package cn.smallshark.service;

import cn.smallshark.entity.InStorageEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
public interface InStorageService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    InStorageEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<InStorageEntity> queryList(Map<String, Object> map);

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
     * @param inStorage 实体
     * @return 保存条数
     */
    int save(InStorageEntity inStorage);

    /**
     * 入库事务
     */


    /**
     * 根据主键更新实体
     *
     * @param inStorage 实体
     * @return 更新条数
     */
    int update(InStorageEntity inStorage);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
