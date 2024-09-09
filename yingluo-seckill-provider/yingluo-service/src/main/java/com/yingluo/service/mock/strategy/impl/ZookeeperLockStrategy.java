package com.yingluo.service.mock.strategy.impl;

import com.yingluo.api.dto.SeckillMockRequestDTO;
import com.yingluo.service.inner.SeckillExecutor;
import com.yingluo.service.mock.strategy.GoodsKillStrategy;
import com.yingluo.service.util.ZookeeperLockUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Component;

import static com.yingluo.core.enums.SeckillSolutionEnum.ZOOKEEPER_LOCK;

/**
 * @author techa03
 * @date 2019/7/27
 */
@Component
@Slf4j
public class ZookeeperLockStrategy implements GoodsKillStrategy {
    @Resource
    private ZookeeperLockUtil zookeeperLockUtil;
    @Resource
    private SeckillExecutor seckillExecutor;

    @Override
    @SneakyThrows
    public void execute(SeckillMockRequestDTO requestDto) {
        long seckillId = requestDto.getSeckillId();
        InterProcessMutex lock = zookeeperLockUtil.lock(seckillId);
        if (lock != null) {
            try {
                seckillExecutor.dealSeckill(seckillId, requestDto.getPhoneNumber(), ZOOKEEPER_LOCK.getName(), requestDto.getTaskId());
            } finally {
                lock.release();
            }
        }
    }
}
