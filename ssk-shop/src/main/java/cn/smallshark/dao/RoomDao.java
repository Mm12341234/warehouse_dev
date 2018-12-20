package cn.smallshark.dao;

import cn.smallshark.entity.RoomEntity;
import cn.smallshark.entity.RoomVo;

import java.util.List;

/**
 * Dao
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
public interface RoomDao extends BaseDao<RoomEntity> {

    List<RoomVo> queryRoomVo(Integer id);
}
