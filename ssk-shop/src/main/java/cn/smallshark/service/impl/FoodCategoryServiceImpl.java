package cn.smallshark.service.impl;

import cn.smallshark.entity.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.FoodCategoryDao;
import cn.smallshark.entity.FoodCategoryEntity;
import cn.smallshark.service.FoodCategoryService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:25
 */
@Service("foodCategoryService")
public class FoodCategoryServiceImpl implements FoodCategoryService {
    @Autowired
    private FoodCategoryDao foodCategoryDao;

    @Override
    public FoodCategoryEntity queryObject(Integer id) {
        return foodCategoryDao.queryObject(id);
    }

    @Override
    public List<FoodCategoryEntity> queryList(Map<String, Object> map) {
        return foodCategoryDao.queryList(map);
    }

    @Override
    public List<FoodCategoryEntity> queryAllList() {
        return foodCategoryDao.queryAllList();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodCategoryDao.queryTotal(map);
    }

    @Override
    public int save(FoodCategoryEntity foodCategory) {
        return foodCategoryDao.save(foodCategory);
    }

    @Override
    public int update(FoodCategoryEntity foodCategory) {
        return foodCategoryDao.update(foodCategory);
    }

    @Override
    public int delete(Integer id) {
        return foodCategoryDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodCategoryDao.deleteBatch(ids);
    }

    @Override
    public List<CategoryVo> queryCateByLevel(Map<String,Object> params){
        return foodCategoryDao.queryCateByLevel(params);
    }

    @Override
    public List<CategoryVo> queryCateByParentId(Map<String,Object> params){
        return foodCategoryDao.queryCateByParentId(params);
    }
}
