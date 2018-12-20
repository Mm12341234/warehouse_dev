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
 * @date 2018-10-25 23:03:40
 */
@Service("sysRegionService")
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
    public int save(SysRegionEntity sysRegion) {
        return sysRegionDao.save(sysRegion);
    }

    @Override
    public int update(SysRegionEntity sysRegion) {
        return sysRegionDao.update(sysRegion);
    }

    @Override
    public int delete(Integer id) {
        return sysRegionDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return sysRegionDao.deleteBatch(ids);
    }

    /**
     * 查找省
     */
    public List<SysRegionEntity> queryProvice(){
        return sysRegionDao.queryProvice();
    }

    /**
     * 查找市
     */
    public List<SysRegionEntity> queryCity(Integer id){
        return sysRegionDao.queryCity(id);
    }
}
