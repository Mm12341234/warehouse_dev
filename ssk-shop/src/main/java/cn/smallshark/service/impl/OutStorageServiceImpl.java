package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.OutStorageDao;
import cn.smallshark.entity.OutStorageEntity;
import cn.smallshark.service.OutStorageService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Service("outStorageService")
public class OutStorageServiceImpl implements OutStorageService {
    @Autowired
    private OutStorageDao outStorageDao;

    @Override
    public OutStorageEntity queryObject(Integer id) {
        return outStorageDao.queryObject(id);
    }

    @Override
    public List<OutStorageEntity> queryList(Map<String, Object> map) {
        return outStorageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return outStorageDao.queryTotal(map);
    }

    @Override
    public int save(OutStorageEntity outStorage) {
        return outStorageDao.save(outStorage);
    }

    @Override
    public int update(OutStorageEntity outStorage) {
        return outStorageDao.update(outStorage);
    }

    @Override
    public int delete(Integer id) {
        return outStorageDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return outStorageDao.deleteBatch(ids);
    }
}
