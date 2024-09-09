package com.yingluo.service.handler;

import com.yingluo.core.pojo.dto.SeckillWebMockRequestDTO;

/**
 * 请求处理类
 */
public interface PreRequestHandler {
    /**
     * @param request
     */
    void handle(SeckillWebMockRequestDTO request);
}
