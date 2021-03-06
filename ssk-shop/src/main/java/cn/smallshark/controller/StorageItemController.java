package cn.smallshark.controller;

import cn.smallshark.entity.StorageItemEntity;
import cn.smallshark.response.StorageItemVo;
import cn.smallshark.service.StorageItemService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
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
 * @date 2018-11-26 20:56:25
 */
@RestController
@RequestMapping("storageitem")
public class StorageItemController {
    @Autowired
    private StorageItemService storageItemService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("storageitem:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<StorageItemEntity> storageItemList = storageItemService.queryList(query);
        int total = storageItemService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(storageItemList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("storageitem:info")
    public R info(@PathVariable("id") Integer id) {
        StorageItemEntity storageItem = storageItemService.queryObject(id);

        return R.ok().put("storageItem", storageItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("storageitem:save")
    public R save(@RequestBody StorageItemEntity storageItem) {
        storageItemService.save(storageItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("storageitem:update")
    public R update(@RequestBody StorageItemEntity storageItem) {
        storageItemService.update(storageItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("storageitem:delete")
    public R delete(@RequestBody Integer[] ids) {
        storageItemService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<StorageItemEntity> list = storageItemService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据不同的仓库来统计各个仓库的总数
     */
    @RequestMapping("/queryAllNumGroupByWareHouse")
    public R queryAllNumGroupByWareHouse(){
        List<StorageItemVo> list=storageItemService.queryAllNumGroupByWareHouse();
        return R.ok().put("list",list);
    }
}
