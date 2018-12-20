package cn.smallshark.service;

import cn.smallshark.entity.RoomEntity;
import cn.smallshark.entity.RoomVo;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
public interface RoomService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    RoomEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<RoomEntity> queryList(Map<String, Object> map);

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
     * @param room 实体
     * @return 保存条数
     */
    int save(RoomEntity room);

    /**
     * 根据主键更新实体
     *
     * @param room 实体
     * @return 更新条数
     */
    int update(RoomEntity room);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(String[] ids);

    List<RoomVo> queryRoomVo(Integer id);
}
