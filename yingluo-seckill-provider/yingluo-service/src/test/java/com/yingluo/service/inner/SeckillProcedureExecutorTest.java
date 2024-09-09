package com.yingluo.service.inner;

import com.yingluo.api.service.SeckillService;
import com.yingluo.service.common.RedisService;
import com.yingluo.service.entity.Seckill;
import com.yingluo.service.mapper.SeckillMapper;
import com.yingluo.service.util.StateMachineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeckillProcedureExecutorTest {
    @InjectMocks
    private SeckillProcedureExecutor seckillProcedureExecutor;
    @Mock
    private SeckillMapper seckillMapper;
    @Mock
    private SeckillService seckillService;
    @Mock
    private StreamBridge streamBridge;
    @Mock
    private RedisService redisService;
    @Mock
    private StateMachineService stateMachineService;

    @Test
    void dealSeckill() {
        long seckillId = 1001L;
        Seckill seckill = new Seckill();
        seckill.setNumber(0);
        when(seckillMapper.selectById(seckillId)).thenReturn(seckill);
        seckillProcedureExecutor.dealSeckill(seckillId, "123", "test", "1");
        Seckill updateSeckill = new Seckill();
        updateSeckill.setSeckillId(seckillId);
//        updateSeckill.setStatus(SeckillStatusConstant.END);
        verify(seckillMapper, times(1)).selectById(seckillId);
    }
}
