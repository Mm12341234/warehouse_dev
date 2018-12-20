package cn.smallshark.dao;

import cn.smallshark.entity.CategoryVo;
import cn.smallshark.entity.FoodCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:25
 */
public interface FoodCategoryDao extends BaseDao<FoodCategoryEntity> {

    List<FoodCategoryEntity> queryAllList();

    List<CategoryVo> queryCateByLevel(Map<String,Object> params);

    List<CategoryVo> queryCateByParentId(Map<String,Object> params);


}
