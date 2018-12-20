package cn.smallshark.service.impl;

import cn.smallshark.dao.AutoOutStorageDao;
import cn.smallshark.entity.AutoOutStorageEntity;
import cn.smallshark.entity.OutStorageEntity;
import cn.smallshark.service.AutoOutStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@Service("autoOutStorageService")
public class AutoOutStorageServiceImpl implements AutoOutStorageService {
    @Autowired
    private AutoOutStorageDao outStorageDao;

    @Override
    public AutoOutStorageEntity queryObject(Integer id) {
        return outStorageDao.queryObject(id);
    }

    @Override
    public List<AutoOutStorageEntity> queryList(Map<String, Object> map) {
        return outStorageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return outStorageDao.queryTotal(map);
    }

    @Override
    public int save(AutoOutStorageEntity outStorage) {
        return outStorageDao.save(outStorage);
    }

    @Override
    public int update(AutoOutStorageEntity outStorage) {
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
