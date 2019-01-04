package cn.smallshark.dao;

import cn.smallshark.entity.CurrentFoodEntity;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
public interface CurrentFoodDao extends BaseDao<CurrentFoodEntity> {

    List<CurrentFoodEntity> queryFoodList();
}
