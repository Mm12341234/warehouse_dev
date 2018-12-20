package cn.smallshark.controller;

import java.util.List;
import java.util.Map;

import cn.smallshark.utils.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.InStorageEntity;
import cn.smallshark.service.InStorageService;
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
@RequestMapping("instorage")
public class InStorageController {
    @Autowired
    private InStorageService inStorageService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("instorage:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<InStorageEntity> inStorageList = inStorageService.queryList(query);
        int total = inStorageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(inStorageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("instorage:info")
    public R info(@PathVariable("id") Integer id) {
        InStorageEntity inStorage = inStorageService.queryObject(id);

        return R.ok().put("inStorage", inStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("instorage:save")
    public R save(@RequestBody InStorageEntity inStorage) {
        String no= CommonUtil.generateOrderNumber();
        inStorage.setNo(no);
        inStorageService.save(inStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("instorage:update")
    public R update(@RequestBody InStorageEntity inStorage) {
        inStorageService.update(inStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("instorage:delete")
    public R delete(@RequestBody Integer[] ids) {
        inStorageService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<InStorageEntity> list = inStorageService.queryList(params);

        return R.ok().put("list", list);
    }
}
