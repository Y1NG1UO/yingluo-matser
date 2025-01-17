package com.yingluo.order.controller;

import com.yingluo.order.entity.OrderDTO;
import com.yingluo.order.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heng
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @DeleteMapping("/deleteRecord")
    public Boolean deleteRecord(long seckillId) {
        return orderService.deleteRecord(seckillId);
    }

    @PostMapping("/saveRecord")
    public Boolean saveRecord(OrderDTO orderDTO) {
        return orderService.saveRecord(orderDTO);
    }

    @GetMapping("/count")
    public Long count(long seckillId) {
        return orderService.count(seckillId);
    }

}
