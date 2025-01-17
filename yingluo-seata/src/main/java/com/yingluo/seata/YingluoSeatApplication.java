package com.yingluo.seata;

import com.yingluo.api.service.GoodsService;
import com.yingluo.api.service.SeckillService;
import com.yingluo.api.vo.GoodsVO;
import com.yingluo.api.vo.SeckillVO;
import com.yingluo.seata.feign.AuthFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.yingluo.seata", "com.yingluo.core"})
@RestController
@EnableFeignClients(basePackages = "com.yingluo.seata.feign")
public class YingluoSeatApplication {
    @DubboReference
    private SeckillService seckillService;
    @DubboReference
    private GoodsService goodsService;
    @Resource
    private AuthFeignClient authFeignClient;

    /**
     * 测试seata分布式事务
     */
    @GetMapping("/test")
    @GlobalTransactional
    public void testGlobalTransaction() {
        GoodsVO goods = new GoodsVO();
        goods.setName("test");
        goodsService.addGoods(goods);
        SeckillVO seckill = seckillService.findById(1001L);
        seckill.setNumber(seckill.getNumber() - 1);
        seckillService.saveOrUpdateSeckill(seckill);
        authFeignClient.addRole(21, 7);
        // 测试异常情况
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        SpringApplication.run(YingluoSeatApplication.class, args);
    }

}
