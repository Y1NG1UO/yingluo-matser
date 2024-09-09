package com.yingluo.service.impl.dubbo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yingluo.api.dto.GoodsDTO;
import com.yingluo.api.service.GoodsEsService;
import com.yingluo.api.service.GoodsService;
import com.yingluo.api.vo.GoodsVO;
import com.yingluo.service.entity.Goods;
import com.yingluo.service.mapper.GoodsMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heng
 * @since 2019-09-07
 */
@DubboService
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Resource
    private GoodsEsService goodsEsService;
    @Resource
    private GoodsMapper baseMapper;

    @Override
    public void uploadGoodsPhoto(long goodsId, String fileUrl) {
        Goods goods = new Goods();
        goods.setGoodsId((int) goodsId);
        goods.setPhotoUrl(fileUrl);
        log.info(goods.toString());
        baseMapper.updateById(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoods(GoodsVO goods) {
        baseMapper.insert(BeanUtil.copyProperties(goods, Goods.class));
        GoodsDTO goodsDto = new GoodsDTO();
        goodsDto.setId(IdWorker.getId());
        BeanUtils.copyProperties(goods, goodsDto);
        try {
            goodsEsService.save(goodsDto);
        } catch (Exception e) {
            log.warn("es服务不可用，请检查！", e);
        }
    }

    @Override
    public GoodsVO findById(Serializable id) {
        Goods byId = super.getById(id);
        return BeanUtil.copyProperties(byId, GoodsVO.class);
    }

    @Override
    public List<GoodsVO> findMany() {
        return BeanUtil.copyToList(super.list(), GoodsVO.class);
    }

}
