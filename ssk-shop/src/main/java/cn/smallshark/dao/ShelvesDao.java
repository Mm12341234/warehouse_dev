package cn.smallshark.dao;

import cn.smallshark.entity.ShelvesEntity;
import cn.smallshark.entity.ShelvesVo;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 22:08:05
 */
public interface ShelvesDao extends BaseDao<ShelvesEntity> {

    List<ShelvesVo> queryShelvesVo(Integer id);
}
