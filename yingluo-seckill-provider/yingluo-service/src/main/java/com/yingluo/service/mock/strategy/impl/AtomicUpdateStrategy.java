package com.yingluo.service.mock.strategy.impl;

import com.yingluo.api.dto.SeckillMockRequestDTO;
import com.yingluo.service.inner.SeckillExecutor;
import com.yingluo.service.mock.strategy.GoodsKillStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yingluo.core.enums.SeckillSolutionEnum.ATOMIC_UPDATE;

/**
 * @author techa03
 * @date 2019/7/27
 */
@Component
@Slf4j
public class AtomicUpdateStrategy implements GoodsKillStrategy {
    @Autowired
    private SeckillExecutor seckillExecutor;

    @Override
    public void execute(SeckillMockRequestDTO requestDto) {
        seckillExecutor.dealSeckill(requestDto.getSeckillId(), requestDto.getPhoneNumber(), ATOMIC_UPDATE.getName(), requestDto.getTaskId());
    }
}
