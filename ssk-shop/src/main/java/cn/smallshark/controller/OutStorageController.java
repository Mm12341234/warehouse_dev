package cn.smallshark.controller;

import java.util.List;
import java.util.Map;

import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.service.CurrentFoodService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.OutStorageEntity;
import cn.smallshark.service.OutStorageService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@RestController
@RequestMapping("outstorage")
public class OutStorageController {
    @Autowired
    private OutStorageService outStorageService;
    @Autowired
    private CurrentFoodService currentFoodService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("outstorage:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<OutStorageEntity> outStorageList = outStorageService.queryList(query);
        int total = outStorageService.queryTotal(query);

//        List<CurrentFoodEntity> currentFoodList=currentFoodService.queryList(query);
//        int total = currentFoodService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(outStorageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("outstorage:info")
    public R info(@PathVariable("id") Integer id) {
        OutStorageEntity outStorage = outStorageService.queryObject(id);

        return R.ok().put("outStorage", outStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("outstorage:save")
    public R save(@RequestBody OutStorageEntity outStorage) {
        outStorageService.save(outStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("outstorage:update")
    public R update(@RequestBody OutStorageEntity outStorage) {
        outStorageService.update(outStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("outstorage:delete")
    public R delete(@RequestBody Integer[] ids) {
        outStorageService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<OutStorageEntity> list = outStorageService.queryList(params);

        return R.ok().put("list", list);
    }
}
