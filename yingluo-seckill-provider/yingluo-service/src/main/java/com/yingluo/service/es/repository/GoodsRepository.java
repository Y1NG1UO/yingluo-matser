package com.yingluo.service.es.repository;

import com.yingluo.service.es.model.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author heng
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, String> {

   void deleteByGoodsId(Integer goodsId);

}
