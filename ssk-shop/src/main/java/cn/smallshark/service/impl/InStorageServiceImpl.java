package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.InStorageDao;
import cn.smallshark.entity.InStorageEntity;
import cn.smallshark.service.InStorageService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Service("inStorageService")
public class InStorageServiceImpl implements InStorageService {
    @Autowired
    private InStorageDao inStorageDao;

    @Override
    public InStorageEntity queryObject(Integer id) {
        return inStorageDao.queryObject(id);
    }

    @Override
    public List<InStorageEntity> queryList(Map<String, Object> map) {
        return inStorageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return inStorageDao.queryTotal(map);
    }

    @Override
    public int save(InStorageEntity inStorage) {
        return inStorageDao.save(inStorage);
    }

    @Override
    public int update(InStorageEntity inStorage) {
        return inStorageDao.update(inStorage);
    }

    @Override
    public int delete(Integer id) {
        return inStorageDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return inStorageDao.deleteBatch(ids);
    }
}
