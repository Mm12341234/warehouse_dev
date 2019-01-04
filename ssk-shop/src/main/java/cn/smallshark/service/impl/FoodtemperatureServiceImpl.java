package cn.smallshark.service.impl;

import cn.smallshark.dao.CurrentFoodDao;
import cn.smallshark.dao.SuitableTemperatureDao;
import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.entity.SuitableTemperatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private CurrentFoodDao currentFoodDao;
    @Autowired
    private SuitableTemperatureDao suitableTemperatureDao;

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
     * 定时模拟根据食物类型的最适温度范围生成温度（多线程）
     */
    public void addTemperature(){
        //模拟读取温度，向数据库插入数据
        // 1. 查询所有食物，归类
        HashMap<Integer, Object> categoryMap = new HashMap<>();
        List<Map<Integer, Object>> categoryList = new ArrayList<>();
        List<CurrentFoodEntity> currentFoodEntities = currentFoodDao.queryFoodList();
        if(currentFoodEntities != null && currentFoodEntities.size() > 0){
            int category;
            for (CurrentFoodEntity currentFoodEntity :  currentFoodEntities) {
                //过滤相同类型的食物，得到食物类型id
                category = currentFoodEntity.getCategoryId();
                categoryMap.put(category,null);
            }
            // 2. 查询食物类型的最适温度范围
            Set<Map.Entry<Integer, Object>> categories = categoryMap.entrySet();
            Iterator<Map.Entry<Integer, Object>> iterator = categories.iterator();
            while(iterator.hasNext()){
                Map.Entry<Integer, Object> categoryEntry = iterator.next();
                Integer key = categoryEntry.getKey();
                //根据key查询食品类型的最适温度范围,将最适温度的实体放入categoryMap中，与类型相对应。
                SuitableTemperatureEntity suitableTemperatureEntity = suitableTemperatureDao.queryObject(key);
                categoryMap.put(key,suitableTemperatureEntity);
            }
            // 3. 模拟根据食物类型的最适温度范围生成温度
            Double minTemperature = null;
            Double maxTemperature = null;
            if(categoryMap != null){
                for (CurrentFoodEntity currentFoodEntity :  currentFoodEntities) {
                    //根据食物类型id，找到对应的最适温度范围。
                    category = currentFoodEntity.getCategoryId();
                    SuitableTemperatureEntity suitableTemperatureEntity = (SuitableTemperatureEntity)categoryMap.get(category);
                    if(suitableTemperatureEntity != null){
                        minTemperature = suitableTemperatureEntity.getMinTemperature(); // 最小存储温度
                        maxTemperature = suitableTemperatureEntity.getMaxTemperature(); // 最大存储温度
                        // 模拟生成食物的温度
                        Random rand = new Random();
                        Double temperature= rand.nextDouble() * (minTemperature + maxTemperature); //模拟温度真实性还得根据实际的情况而定（这里生成的温度并不准确）
                        FoodtemperatureEntity foodtemperature=new FoodtemperatureEntity();
                        foodtemperature.setFoodid(currentFoodEntity.getId());
                        foodtemperature.setFoodtemperature(temperature);
                        foodtemperature.setTemperaturetime(new Date());
                        foodtemperatureDao.save(foodtemperature);
                    }
                }
            }
        }
    }
}
