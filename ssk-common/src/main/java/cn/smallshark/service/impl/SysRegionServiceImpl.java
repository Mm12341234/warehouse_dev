package cn.smallshark.service.impl;

import cn.smallshark.dao.SysRegionDao;
import cn.smallshark.entity.SysRegionEntity;
import cn.smallshark.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-11-04 11:19:31
 */
@Service("regionService")
public class SysRegionServiceImpl implements SysRegionService {
    @Autowired
    private SysRegionDao sysRegionDao;

    @Override
    public SysRegionEntity queryObject(Integer id) {
        return sysRegionDao.queryObject(id);
    }

    @Override
    public List<SysRegionEntity> queryList(Map<String, Object> map) {
        return sysRegionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRegionDao.queryTotal(map);
    }

    @Override
    public int save(SysRegionEntity region) {
        return sysRegionDao.save(region);
    }

    @Override
    public int update(SysRegionEntity region) {
        return sysRegionDao.update(region);
    }

    @Override
    public int delete(Integer id) {
        return sysRegionDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return sysRegionDao.deleteBatch(ids);
    }
}
