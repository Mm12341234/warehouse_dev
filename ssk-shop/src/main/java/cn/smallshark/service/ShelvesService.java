package cn.smallshark.service;

import cn.smallshark.entity.ShelvesEntity;
import cn.smallshark.entity.ShelvesVo;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 22:08:05
 */
public interface ShelvesService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ShelvesEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ShelvesEntity> queryList(Map<String, Object> map);

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
     * @param shelves 实体
     * @return 保存条数
     */
    int save(ShelvesEntity shelves);

    /**
     * 根据主键更新实体
     *
     * @param shelves 实体
     * @return 更新条数
     */
    int update(ShelvesEntity shelves);

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

    /**
     *
     */
    List<ShelvesVo> queryShelvesVo(Integer id);
}
