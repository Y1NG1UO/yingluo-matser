package com.yingluo.service.handler;

import com.yingluo.core.constant.SeckillStatusConstant;
import com.yingluo.core.pojo.dto.SeckillWebMockRequestDTO;
import com.yingluo.service.common.RedisService;
import com.yingluo.service.entity.Seckill;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class RedisPreRequestHandler extends AbstractPreRequestHandler {
    @Resource
    private RedisService redisService;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void handle(SeckillWebMockRequestDTO request) {
        long seckillId = request.getSeckillId();
        redisService.removeSeckill(seckillId);
        Seckill seckill = redisService.getSeckill(seckillId);
        seckill.setStatus(SeckillStatusConstant.IN_PROGRESS);
        redisService.putSeckill(seckill);
        redisService.clearSeckillEndFlag(seckillId, request.getTaskId());
    }
}
