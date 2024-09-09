package com.yingluo.order.service.impl;

import com.yingluo.order.entity.Order;
import com.yingluo.order.entity.OrderDTO;
import com.yingluo.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

/**
 * @author heng
 */
@Slf4j
@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    public Boolean deleteRecord(long seckillId) {
        orderRepository.deleteBySeckillId(BigInteger.valueOf(seckillId));
        return true;
    }

    public Boolean saveRecord(OrderDTO orderDTO) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .seckillId(orderDTO.getSeckillId())
                .userPhone(orderDTO.getUserPhone())
                .build();
        orderRepository.insert(order);
        log.info("保存成功,{}", order);
        return true;
    }

    public Long count(long seckillId) {
        return orderRepository.countBySeckillId(BigInteger.valueOf(seckillId));
    }

}
