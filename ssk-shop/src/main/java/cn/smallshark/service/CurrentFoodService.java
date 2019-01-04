package cn.smallshark.service;

import cn.smallshark.entity.CurrentFoodEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
public interface CurrentFoodService {

    /**
     * 查询所有的实体
     * @return
     */
//    List<CurrentFoodEntity> queryList();

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CurrentFoodEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CurrentFoodEntity> queryList(Map<String, Object> map);

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
     * @param currentFood 实体
     * @return 保存条数
     */
    int save(CurrentFoodEntity currentFood);

    /**
     * 根据主键更新实体
     *
     * @param currentFood 实体
     * @return 更新条数
     */
    int update(CurrentFoodEntity currentFood);

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
}
