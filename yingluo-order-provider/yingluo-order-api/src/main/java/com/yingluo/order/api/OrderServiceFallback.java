//package com.yingluo.order.api;
//
//import com.yingluo.order.api.OrderService;
//import com.yingluo.order.entity.OrderDTO;
//import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderServiceFallback implements OrderService {
//    public Boolean deleteRecord(long seckillId) {
//        return false;
//    }
//
//    public Boolean saveRecord(OrderDTO orderDTO) {
//        return false;
//    }
//
//    public long count(long seckillId) {
//        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
//    }
//}
