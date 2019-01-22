package cn.smallshark.dao;

import cn.smallshark.entity.WarmEntity;
import cn.smallshark.response.WarmVo;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2019-01-05 10:25:46
 */
public interface WarmDao extends BaseDao<WarmEntity> {
    List<WarmVo> queryAllNumGroupByWareHouse();
}
