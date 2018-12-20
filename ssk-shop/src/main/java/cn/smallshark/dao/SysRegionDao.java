package cn.smallshark.dao;

import cn.smallshark.entity.SysRegionEntity;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-10-25 23:03:40
 */
public interface SysRegionDao extends BaseDao<SysRegionEntity> {

    /**
     * 查找省
     */
    List<SysRegionEntity> queryProvice();

    /**
     * 查找市
     */
    List<SysRegionEntity> queryCity(Integer id);
}
