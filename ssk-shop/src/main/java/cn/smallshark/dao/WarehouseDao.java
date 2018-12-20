package cn.smallshark.dao;

import cn.smallshark.entity.WarehouseEntity;
import cn.smallshark.entity.WarehouseVo;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:58
 */
public interface WarehouseDao extends BaseDao<WarehouseEntity> {
    List<WarehouseEntity> queryHouseAll();

    List<WarehouseVo> queryWarehouseVo();
}
