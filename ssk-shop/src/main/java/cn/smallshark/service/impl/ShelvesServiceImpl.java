package cn.smallshark.service.impl;

import cn.smallshark.entity.ShelvesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.ShelvesDao;
import cn.smallshark.entity.ShelvesEntity;
import cn.smallshark.service.ShelvesService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 22:08:05
 */
@Service("shelvesService")
public class ShelvesServiceImpl implements ShelvesService {
    @Autowired
    private ShelvesDao shelvesDao;

    @Override
    public ShelvesEntity queryObject(Integer id) {
        return shelvesDao.queryObject(id);
    }

    @Override
    public List<ShelvesEntity> queryList(Map<String, Object> map) {
        return shelvesDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return shelvesDao.queryTotal(map);
    }

    @Override
    public int save(ShelvesEntity shelves) {
        return shelvesDao.save(shelves);
    }

    @Override
    public int update(ShelvesEntity shelves) {
        return shelvesDao.update(shelves);
    }

    @Override
    public int delete(Integer id) {
        return shelvesDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return shelvesDao.deleteBatch(ids);
    }

    @Override
    public List<ShelvesVo> queryShelvesVo(Integer id){
        return shelvesDao.queryShelvesVo(id);
    }
}
