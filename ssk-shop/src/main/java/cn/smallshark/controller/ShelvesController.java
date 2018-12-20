package cn.smallshark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smallshark.entity.ShelvesVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.ShelvesEntity;
import cn.smallshark.service.ShelvesService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 22:08:05
 */
@RestController
@RequestMapping("shelves")
public class ShelvesController {
    @Autowired
    private ShelvesService shelvesService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shelves:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ShelvesEntity> shelvesList = shelvesService.queryList(query);
        int total = shelvesService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(shelvesList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shelves:info")
    public R info(@PathVariable("id") Integer id) {
        ShelvesEntity shelves = shelvesService.queryObject(id);

        return R.ok().put("shelves", shelves);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shelves:save")
    public R save(@RequestBody ShelvesEntity shelves) {
        shelvesService.save(shelves);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shelves:update")
    public R update(@RequestBody ShelvesEntity shelves) {
        shelvesService.update(shelves);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shelves:delete")
    public R delete(@RequestBody Integer[] ids) {
        shelvesService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ShelvesEntity> list = shelvesService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看货架的id和name
     */
    @RequestMapping("/queryShelvesVo/{id}")
    public R queryShelvesVo(@PathVariable("id") Integer id){
        List<ShelvesVo> list=shelvesService.queryShelvesVo(id);
        return R.ok().put("list",list);
    }

    /**
     * 根据仓库的楼层以及货架的层数，查找在库仓中的所有事物
     */

}
