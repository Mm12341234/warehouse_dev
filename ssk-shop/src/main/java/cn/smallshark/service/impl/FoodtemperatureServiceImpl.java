package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.smallshark.dao.FoodtemperatureDao;
import cn.smallshark.entity.FoodtemperatureEntity;
import cn.smallshark.service.FoodtemperatureService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
@Service("foodtemperatureService")
public class FoodtemperatureServiceImpl implements FoodtemperatureService {
    @Autowired
    private FoodtemperatureDao foodtemperatureDao;

    @Override
    public FoodtemperatureEntity queryNewFoodTemperatureById(Integer id) {
        return foodtemperatureDao.queryNewFoodTemperatureById(id);
    }

    @Override
    public FoodtemperatureEntity queryObject(Integer id) {
        return foodtemperatureDao.queryObject(id);
    }

    @Override
    public List<FoodtemperatureEntity> queryList(Map<String, Object> map) {
        return foodtemperatureDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodtemperatureDao.queryTotal(map);
    }

    @Override
    public int save(FoodtemperatureEntity foodtemperature) {
        return foodtemperatureDao.save(foodtemperature);
    }

    @Override
    public int update(FoodtemperatureEntity foodtemperature) {
        return foodtemperatureDao.update(foodtemperature);
    }

    @Override
    public int delete(Integer id) {
        return foodtemperatureDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodtemperatureDao.deleteBatch(ids);
    }

    /**
     * 查看食物的温度
     */
    public List<FoodtemperatureEntity> queryTemperature(Integer id){
        return foodtemperatureDao.queryTemperature(id);
    }

    /**
     * 定时测试
     */
    public void addTemperature(){
        //模拟读取温度，向数据库插入数据
        Random rand = new Random();
        Integer foodId=rand.nextInt(20);
        Double temperature=rand.nextDouble() * 10;
        FoodtemperatureEntity foodtemperature=new FoodtemperatureEntity();
        foodtemperature.setFoodid(foodId);
        foodtemperature.setFoodtemperature(temperature);
        foodtemperature.setTemperaturetime(new Date());
        foodtemperatureDao.save(foodtemperature);
    }
}
