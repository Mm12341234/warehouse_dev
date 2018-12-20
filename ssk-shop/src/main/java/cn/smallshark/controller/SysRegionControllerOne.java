package cn.smallshark.controller;

import cn.smallshark.entity.SysRegionEntity;
import cn.smallshark.service.SysRegionService;
import cn.smallshark.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-10-25 23:03:40
 */
@RestController
@RequestMapping("sysregion")
public class SysRegionControllerOne {
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 查找省级地方
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("sysregion:info")
    public R info(@PathVariable("id") Integer id) {

        List<SysRegionEntity> list = sysRegionService.queryProvice();

        return R.ok().put("list", list);
    }

    /**
     * 市级联动
     */
    @RequestMapping("/infoCity/{id}")
    public R infoCity(@PathVariable("id") Integer id) {

        List<SysRegionEntity> list = sysRegionService.queryCity(id);

        return R.ok().put("list", list);
    }
    /**
     *  根据id查找
     */
    @RequestMapping("/objectInfo/{id}")
    public R showArea(@PathVariable("id") Integer id) {

        SysRegionEntity list = sysRegionService.queryObject(id);

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sysregion:save")
    public R save(@RequestBody SysRegionEntity sysRegion) {
        sysRegionService.save(sysRegion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sysregion:update")
    public R update(@RequestBody SysRegionEntity sysRegion) {
        sysRegionService.update(sysRegion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sysregion:delete")
    public R delete(@RequestBody Integer[] ids) {
        sysRegionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysRegionEntity> list = sysRegionService.queryList(params);

        return R.ok().put("list", list);
    }
}
