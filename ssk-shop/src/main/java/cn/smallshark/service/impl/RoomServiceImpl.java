package cn.smallshark.service.impl;

import cn.smallshark.entity.RoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.RoomDao;
import cn.smallshark.entity.RoomEntity;
import cn.smallshark.service.RoomService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    @Override
    public RoomEntity queryObject(String id) {
        return roomDao.queryObject(id);
    }

    @Override
    public List<RoomEntity> queryList(Map<String, Object> map) {
        return roomDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return roomDao.queryTotal(map);
    }

    @Override
    public int save(RoomEntity room) {
        return roomDao.save(room);
    }

    @Override
    public int update(RoomEntity room) {
        return roomDao.update(room);
    }

    @Override
    public int delete(String id) {
        return roomDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return roomDao.deleteBatch(ids);
    }

    @Override
    public List<RoomVo> queryRoomVo(Integer id) {
        return roomDao.queryRoomVo(id);
    }
}
