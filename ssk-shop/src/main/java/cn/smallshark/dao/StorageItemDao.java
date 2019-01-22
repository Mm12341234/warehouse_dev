package cn.smallshark.dao;

import cn.smallshark.entity.StorageItemEntity;
import cn.smallshark.response.StorageItemVo;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
public interface StorageItemDao extends BaseDao<StorageItemEntity> {
    StorageItemEntity queryObjectByPayNo(String no);

    List<StorageItemVo> queryAllNumGroupByWareHouse();
}
