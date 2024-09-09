package com.yingluo.service.handler;


import com.yingluo.core.pojo.dto.SeckillWebMockRequestDTO;
import org.springframework.core.Ordered;

public abstract class AbstractPreRequestHandler implements PreRequestHandler, Ordered {

    /**
     * @param request
     */
    @Override
    public abstract void handle(SeckillWebMockRequestDTO request);

}
