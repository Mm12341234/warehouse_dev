package cn.smallshark.service;

import cn.smallshark.entity.FoodtemperatureEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
public interface FoodtemperatureService {

    /**
     * 根据Id获取该食品当前最新的温度情况
     * @Author carvin
     * @date 2018.12.25
     * @return
     */
    FoodtemperatureEntity queryNewFoodTemperatureById(Integer id);

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    FoodtemperatureEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<FoodtemperatureEntity> queryList(Map<String, Object> map);

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
     * @param foodtemperature 实体
     * @return 保存条数
     */
    int save(FoodtemperatureEntity foodtemperature);

    /**
     * 根据主键更新实体
     *
     * @param foodtemperature 实体
     * @return 更新条数
     */
    int update(FoodtemperatureEntity foodtemperature);

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
     * 查找食物的温度
     */
    List<FoodtemperatureEntity> queryTemperature(Integer id);

}
