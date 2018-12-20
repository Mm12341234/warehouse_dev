package cn.smallshark.service;

import cn.smallshark.entity.CategoryVo;
import cn.smallshark.entity.FoodCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:25
 */
public interface FoodCategoryService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    FoodCategoryEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<FoodCategoryEntity> queryList(Map<String, Object> map);

    /**
     *
     * @return list
     */
    List<FoodCategoryEntity> queryAllList();

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param foodCategory 实体
     * @return 保存条数
     */
    int save(FoodCategoryEntity foodCategory);

    /**
     * 根据主键更新实体
     *
     * @param foodCategory 实体
     * @return 更新条数
     */
    int update(FoodCategoryEntity foodCategory);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
    /**
     * 根据分类的等级查找所有的分类
     */
    List<CategoryVo> queryCateByLevel(Map<String,Object> params);
    List<CategoryVo> queryCateByParentId(Map<String,Object> params);
}
