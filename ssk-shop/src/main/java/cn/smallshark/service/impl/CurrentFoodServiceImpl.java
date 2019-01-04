package cn.smallshark.service.impl;

import cn.smallshark.dao.StorageItemDao;
import cn.smallshark.entity.StorageItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.CurrentFoodDao;
import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.service.CurrentFoodService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
@Service("currentFoodService")
public class CurrentFoodServiceImpl implements CurrentFoodService {
    @Autowired
    private CurrentFoodDao currentFoodDao;
    @Autowired
    private StorageItemDao storageItemDao;

    @Override
    public CurrentFoodEntity queryObject(Integer id) {
        return currentFoodDao.queryObject(id);
    }

    @Override
    public List<CurrentFoodEntity> queryList(Map<String, Object> map) {
        return currentFoodDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return currentFoodDao.queryTotal(map);
    }

    @Override
    public int save(CurrentFoodEntity currentFood) {
        return currentFoodDao.save(currentFood);
    }

    @Override
    public int update(CurrentFoodEntity currentFood) {
        return currentFoodDao.update(currentFood);
    }

    @Override
    public int delete(Integer id) {
        return currentFoodDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return currentFoodDao.deleteBatch(ids);
    }

    @Override
    @Transactional
    public int inStorage(CurrentFoodEntity currentFood, StorageItemEntity storageItem){
        storageItemDao.save(storageItem);
        return currentFoodDao.save(currentFood);
    }

    @Override
    @Transactional
    public int outStorage(CurrentFoodEntity currentFood, StorageItemEntity storageItem){
        storageItemDao.update(storageItem);
        return currentFoodDao.update(currentFood);
    }
}
