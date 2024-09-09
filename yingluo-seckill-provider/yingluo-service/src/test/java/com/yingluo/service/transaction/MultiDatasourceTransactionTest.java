package com.yingluo.service.transaction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yingluo.service.common.SuccessKilledService;
import com.yingluo.service.entity.Goods;
import com.yingluo.service.entity.SuccessKilled;
import com.yingluo.service.mapper.GoodsMapper;
import com.yingluo.service.mapper.SuccessKilledMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 多数据源事务测试
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Disabled
public class MultiDatasourceTransactionTest {
    @Autowired
    private SuccessKilledMapper successKilledMapper;
    @Autowired
    private SuccessKilledService successKilledService;
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    @Transactional
    public void testInsert() {
        Goods entity = new Goods();
        entity.setName("test");
        goodsMapper.insert(entity);

        SuccessKilled su = new SuccessKilled();
        su.setUserPhone("3434");
        su.setSeckillId(1432L);
        successKilledMapper.insert(su);

        su = new SuccessKilled();
        su.setUserPhone("3435");
        su.setSeckillId(1432L);
        successKilledMapper.insert(su);

        su = new SuccessKilled();
        su.setUserPhone("3435");
        su.setSeckillId(1431L);
        successKilledMapper.insert(su);

        su = new SuccessKilled();
        su.setUserPhone("3434");
        su.setSeckillId(1431L);
        successKilledMapper.insert(su);
    }

    @Test
    public void testPage() {
        IPage page = successKilledService.page(new Page<>(1,100));
        assertTrue(page.getSize() > 0);
    }

    @Test
    public void testPage1() {
        IPage page = goodsMapper.selectPage(new Page(1,2), null);
        assertTrue(page.getSize() > 0);
    }


}
