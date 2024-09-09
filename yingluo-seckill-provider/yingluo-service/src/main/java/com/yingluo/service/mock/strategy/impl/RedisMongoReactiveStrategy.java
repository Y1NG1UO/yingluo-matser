package com.yingluo.service.mock.strategy.impl;

import com.yingluo.api.dto.SeckillMockRequestDTO;
import com.yingluo.api.dto.SeckillMockResponseDTO;
import com.yingluo.core.constant.SeckillStatusConstant;
import com.yingluo.core.enums.Events;
import com.yingluo.core.enums.States;
import com.yingluo.order.vo.SeckillMockSaveVo;
import com.yingluo.service.common.RedisService;
import com.yingluo.service.entity.Seckill;
import com.yingluo.service.mock.strategy.GoodsKillStrategy;
import com.yingluo.service.util.StateMachineService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

import static com.yingluo.core.enums.SeckillSolutionEnum.REDIS_MONGO_REACTIVE;
import static com.yingluo.service.common.constant.CommonConstant.DEFAULT_BINDING_NAME;
import static com.yingluo.service.common.constant.CommonConstant.DEFAULT_BINDING_NAME_MONGO_SAVE;

/**
 * @author techa03
 * @date 2019/7/27
 */
@Component
@Slf4j
public class RedisMongoReactiveStrategy implements GoodsKillStrategy {
    @Resource
    private RedisService redisService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "taskExecutor")
    private ThreadPoolExecutor taskExecutor;
    @Resource
    private StreamBridge streamBridge;
    @Resource
    private StateMachineService stateMachineService;

    @Override
    public void execute(SeckillMockRequestDTO requestDto) {
        taskExecutor.execute(() -> {
            try {
                doExecute(requestDto);
            } catch (Exception e) {
                log.error("秒杀失败", e);
            }
        });
    }

    private void doExecute(SeckillMockRequestDTO requestDto) {
        long seckillId = requestDto.getSeckillId();
        Seckill seckill = redisService.getSeckill(seckillId);
        if (redisTemplate.opsForValue().increment(String.valueOf(seckillId)) <= seckill.getNumber()) {
            taskExecutor.execute(() ->
                    streamBridge.send(DEFAULT_BINDING_NAME_MONGO_SAVE, MessageBuilder.withPayload(
                            SeckillMockSaveVo
                                    .builder()
                                    .seckillId(seckillId)
                                    .userPhone(requestDto.getPhoneNumber())
                                    .note(REDIS_MONGO_REACTIVE.getName())
                                    .build())
                            .build())
            );
        } else {
            synchronized (this) {
                seckill = redisService.getSeckill(seckillId);
                if (stateMachineService.checkState(seckillId, States.IN_PROGRESS)) {
                    stateMachineService.feedMachine(Events.ACTIVITY_CALCULATE, seckillId);
                    log.info("秒杀商品暂无库存，发送活动结束消息！");
                    streamBridge.send(DEFAULT_BINDING_NAME, MessageBuilder.withPayload(
                            SeckillMockResponseDTO
                                    .builder()
                                    .status(true)
                                    .seckillId(seckillId)
                                    .note(REDIS_MONGO_REACTIVE.getName())
                                    .taskId(requestDto.getTaskId())
                                    .build())
                            .build());
                    seckill.setStatus(SeckillStatusConstant.END);
                    redisService.putSeckill(seckill);
                }
            }
        }
    }
}
