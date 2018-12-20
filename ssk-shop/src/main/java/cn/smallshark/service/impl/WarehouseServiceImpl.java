package cn.smallshark.service.impl;

import cn.smallshark.entity.WarehouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.WarehouseDao;
import cn.smallshark.entity.WarehouseEntity;
import cn.smallshark.service.WarehouseService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:58
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public WarehouseEntity queryObject(String id) {
        return warehouseDao.queryObject(id);
    }

    @Override
    public List<WarehouseEntity> queryList(Map<String, Object> map) {
        return warehouseDao.queryList(map);
    }

    @Override
    public List<WarehouseEntity> queryHouseAll() {
        return warehouseDao.queryHouseAll();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return warehouseDao.queryTotal(map);
    }

    @Override
    public int save(WarehouseEntity warehouse) {
        return warehouseDao.save(warehouse);
    }

    @Override
    public int update(WarehouseEntity warehouse) {
        return warehouseDao.update(warehouse);
    }

    @Override
    public int delete(String id) {
        return warehouseDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return warehouseDao.deleteBatch(ids);
    }

    public List<WarehouseVo> queryWarehouseVo(){
        return warehouseDao.queryWarehouseVo();
    }
}
