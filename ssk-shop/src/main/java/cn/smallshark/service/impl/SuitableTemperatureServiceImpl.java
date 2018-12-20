package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.SuitableTemperatureDao;
import cn.smallshark.entity.SuitableTemperatureEntity;
import cn.smallshark.service.SuitableTemperatureService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:26
 */
@Service("suitableTemperatureService")
public class SuitableTemperatureServiceImpl implements SuitableTemperatureService {
    @Autowired
    private SuitableTemperatureDao suitableTemperatureDao;

    @Override
    public SuitableTemperatureEntity queryObject(Integer id) {
        return suitableTemperatureDao.queryObject(id);
    }

    @Override
    public List<SuitableTemperatureEntity> queryList(Map<String, Object> map) {
        return suitableTemperatureDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return suitableTemperatureDao.queryTotal(map);
    }

    @Override
    public int save(SuitableTemperatureEntity suitableTemperature) {
        return suitableTemperatureDao.save(suitableTemperature);
    }

    @Override
    public int update(SuitableTemperatureEntity suitableTemperature) {
        return suitableTemperatureDao.update(suitableTemperature);
    }

    @Override
    public int delete(Integer id) {
        return suitableTemperatureDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return suitableTemperatureDao.deleteBatch(ids);
    }
}
