package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.StorageItemDao;
import cn.smallshark.entity.StorageItemEntity;
import cn.smallshark.service.StorageItemService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Service("storageItemService")
public class StorageItemServiceImpl implements StorageItemService {
    @Autowired
    private StorageItemDao storageItemDao;

    @Override
    public StorageItemEntity queryObject(Integer id) {
        return storageItemDao.queryObject(id);
    }

    @Override
    public List<StorageItemEntity> queryList(Map<String, Object> map) {
        return storageItemDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return storageItemDao.queryTotal(map);
    }

    @Override
    public int save(StorageItemEntity storageItem) {
        return storageItemDao.save(storageItem);
    }

    @Override
    public int update(StorageItemEntity storageItem) {
        return storageItemDao.update(storageItem);
    }

    @Override
    public int delete(Integer id) {
        return storageItemDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return storageItemDao.deleteBatch(ids);
    }
}
