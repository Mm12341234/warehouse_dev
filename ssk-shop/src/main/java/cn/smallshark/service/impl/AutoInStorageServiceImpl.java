package cn.smallshark.service.impl;

import cn.smallshark.dao.AutoInStorageDao;
import cn.smallshark.dao.CurrentFoodDao;
import cn.smallshark.dao.InStorageDao;
import cn.smallshark.dao.StorageItemDao;
import cn.smallshark.entity.AutoInStorageEntity;
import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.entity.InStorageEntity;
import cn.smallshark.entity.StorageItemEntity;
import cn.smallshark.service.AutoInStorageService;
import cn.smallshark.service.InStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Service("autoInStorageService")
public class AutoInStorageServiceImpl implements AutoInStorageService {
    @Autowired
    private AutoInStorageDao autoInStorageDao;
    @Autowired
    private CurrentFoodDao currentFoodDao;
    @Autowired
    private StorageItemDao storageItemDao;

    @Override
    public AutoInStorageEntity queryObject(Integer id) {
        return autoInStorageDao.queryObject(id);
    }

    @Override
    public List<AutoInStorageEntity> queryList(Map<String, Object> map) {
        return autoInStorageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return autoInStorageDao.queryTotal(map);
    }

    @Override
    public int save(AutoInStorageEntity inStorage) {
        return autoInStorageDao.save(inStorage);
    }

    @Override
    public int update(AutoInStorageEntity inStorage) {
        return autoInStorageDao.update(inStorage);
    }

    @Override
    public int delete(Integer id) {
        return autoInStorageDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return autoInStorageDao.deleteBatch(ids);
    }

    @Override
    @Transactional
    public int agreeInStorage(AutoInStorageEntity autoInStorage, CurrentFoodEntity foodEntity, StorageItemEntity storageItem){
        autoInStorageDao.update(autoInStorage);
        currentFoodDao.save(foodEntity);
       return storageItemDao.save(storageItem);
    }

    @Override
    @Transactional
    public int unAgreeInStorage(AutoInStorageEntity autoInStorage,StorageItemEntity storageItem){
        autoInStorageDao.update(autoInStorage);
        return storageItemDao.save(storageItem);
    }
}
