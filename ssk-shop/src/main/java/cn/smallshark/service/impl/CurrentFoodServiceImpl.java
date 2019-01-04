package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.CurrentFoodDao;
import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.service.CurrentFoodService;

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

    /*@Override
    public List<CurrentFoodEntity> queryList() { return currentFoodDao.queryList(id)};*/

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
}
