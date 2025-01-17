package com.yingluo.service.mock.strategy.impl;

import com.yingluo.api.dto.SeckillMockRequestDTO;
import com.yingluo.service.inner.SeckillExecutor;
import com.yingluo.service.mock.strategy.GoodsKillStrategy;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yingluo.core.enums.SeckillSolutionEnum.REDISSION_LOCK;

/**
 * @author techa03
 * @date 2019/7/27
 */
@Component
@Slf4j
public class RedissonStrategy implements GoodsKillStrategy {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SeckillExecutor seckillExecutor;


    @Override
    public void execute(SeckillMockRequestDTO requestDto) {
        RLock lock = redissonClient.getLock(requestDto.getSeckillId() + "");
        lock.lock();
        try {
            seckillExecutor.dealSeckill(requestDto.getSeckillId(), requestDto.getPhoneNumber(), REDISSION_LOCK.getName(), requestDto.getTaskId());
        } finally {
            lock.unlock();
        }
    }

}
