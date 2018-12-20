package cn.smallshark.dao;

import cn.smallshark.entity.GoodsVo;

import java.util.List;
import java.util.Map;

/**
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-08-11 09:16:45
 */
public interface ApiGoodsMapper extends BaseDao<GoodsVo> {

    List<GoodsVo> queryHotGoodsList(Map<String, Object> params);

    List<GoodsVo> queryCatalogProductList(Map<String, Object> params);
}
