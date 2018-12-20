package cn.smallshark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smallshark.entity.ShelvesEntity;
import cn.smallshark.entity.WarehouseVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.WarehouseEntity;
import cn.smallshark.service.WarehouseService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:58
 */
@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("warehouse:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<WarehouseEntity> warehouseList = warehouseService.queryList(query);
        int total = warehouseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(warehouseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看仓库所有数据
     */
    @RequestMapping("/queryHouseAll")
    public R queryAll() {

        List<WarehouseEntity> warehouseList = warehouseService.queryHouseAll();

        return R.ok().put("list", warehouseList);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("warehouse:info")
    public R info(@PathVariable("id") String id) {
        WarehouseEntity warehouse = warehouseService.queryObject(id);

        return R.ok().put("warehouse", warehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("warehouse:save")
    public R save(@RequestBody WarehouseEntity warehouse) {
        warehouseService.save(warehouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("warehouse:update")
    public R update(@RequestBody WarehouseEntity warehouse) {
        warehouseService.update(warehouse);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("warehouse:delete")
    public R delete(@RequestBody String[] ids) {
        warehouseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<WarehouseEntity> list = warehouseService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAllById/{id}")
    public R queryAllById(@PathVariable("id") Integer id) {

        List<WarehouseVo> list = warehouseService.queryWarehouseVo();
        return R.ok().put("list", list);
    }


}
