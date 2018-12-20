package cn.smallshark.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.FoodtemperatureEntity;
import cn.smallshark.service.FoodtemperatureService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
@RestController
@RequestMapping("foodtemperature")
public class FoodtemperatureController {
    @Autowired
    private FoodtemperatureService foodtemperatureService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodtemperature:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodtemperatureEntity> foodtemperatureList = foodtemperatureService.queryList(query);
        int total = foodtemperatureService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodtemperatureList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodtemperature:info")
    public R info(@PathVariable("id") Integer id) {
        FoodtemperatureEntity foodtemperature = foodtemperatureService.queryObject(id);

        return R.ok().put("foodtemperature", foodtemperature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodtemperature:save")
    public R save(@RequestBody FoodtemperatureEntity foodtemperature) {
        foodtemperatureService.save(foodtemperature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodtemperature:update")
    public R update(@RequestBody FoodtemperatureEntity foodtemperature) {
        foodtemperatureService.update(foodtemperature);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodtemperature:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodtemperatureService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodtemperatureEntity> list = foodtemperatureService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取食物的温度
     */
    @RequestMapping("/queryTemperature/{id}")
    public R queryTemperature(@PathVariable("id") Integer id){
        List<FoodtemperatureEntity> list = foodtemperatureService.queryTemperature(id);
        return R.ok().put("list",list);
    }

    @RequestMapping("/addTemperature")
    public void addTemperature(){
        System.out.println("你好");
    }
}
