package com.yingluo.service.controller;

import com.yingluo.api.service.GoodsEsService;
import com.yingluo.api.service.GoodsService;
import com.yingluo.api.service.SeckillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

class SeckillControllerTest {
    @Mock
    Logger logger;
    @Mock
    SeckillService seckillService;
    @Mock
    GoodsService goodsService;
    @Mock
    GoodsEsService goodsEsService;
    @InjectMocks
    SeckillController seckillController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testUserPhoneCode() {
//        seckillController.userPhoneCode(Long.valueOf(1));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
