package cn.smallshark.dao;

import cn.smallshark.entity.FoodtemperatureEntity;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
public interface FoodtemperatureDao extends BaseDao<FoodtemperatureEntity> {

    List<FoodtemperatureEntity> queryTemperature(Integer id);

    FoodtemperatureEntity queryNewFoodTemperatureById(Integer id);
}
