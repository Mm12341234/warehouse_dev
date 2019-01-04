package cn.smallshark.controller;

import java.util.List;
import java.util.Map;

import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.entity.FoodtemperatureEntity;
import cn.smallshark.service.CurrentFoodService;
import cn.smallshark.service.FoodtemperatureService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.smallshark.entity.SuitableTemperatureEntity;
import cn.smallshark.service.SuitableTemperatureService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:26
 */
@RestController
@RequestMapping("suitabletemperature")
public class SuitableTemperatureController {
    @Autowired
    private SuitableTemperatureService suitableTemperatureService;

    @Autowired
    private CurrentFoodService currentFoodService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("suitabletemperature:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SuitableTemperatureEntity> suitableTemperatureList = suitableTemperatureService.queryList(query);
        int total = suitableTemperatureService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(suitableTemperatureList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    //@RequiresPermissions("suitabletemperature:info")
    public R info(@PathVariable("id") Integer id) {
        SuitableTemperatureEntity suitableTemperature = null;
        CurrentFoodEntity currentFoodEntity = currentFoodService.queryObject(id);
        if(currentFoodEntity != null){
             Integer category = currentFoodEntity.getCategoryId();
             suitableTemperature = suitableTemperatureService.queryObject(category);
        }
        return R.ok().put("suitableTemperature", suitableTemperature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("suitabletemperature:save")
    public R save(@RequestBody SuitableTemperatureEntity suitableTemperature) {
        suitableTemperatureService.save(suitableTemperature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("suitabletemperature:update")
    public R update(@RequestBody SuitableTemperatureEntity suitableTemperature) {
        suitableTemperatureService.update(suitableTemperature);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("suitabletemperature:delete")
    public R delete(@RequestBody Integer[] ids) {
        suitableTemperatureService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SuitableTemperatureEntity> list = suitableTemperatureService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据分类的id查找合适的温度
     */
    @RequestMapping("/queryByCateId/{id}")
    public R queryByCateId(@PathVariable("id") Integer id){
        SuitableTemperatureEntity suitableTemperature = suitableTemperatureService.queryObjectByCateId(id);

        return R.ok().put("suitableTemperature", suitableTemperature);
    }
}
