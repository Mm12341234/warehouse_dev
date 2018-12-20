package cn.smallshark.controller;

import cn.smallshark.entity.AutoInStorageEntity;
import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.entity.StorageItemEntity;
import cn.smallshark.service.AutoInStorageService;
import cn.smallshark.utils.CommonUtil;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("autoinstorage")
public class AutoInStorageController {
    @Autowired
    private AutoInStorageService autoInStorageService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("instorage:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AutoInStorageEntity> autoInStorageList = autoInStorageService.queryList(query);
        int total = autoInStorageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(autoInStorageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("instorage:info")
    public R info(@PathVariable("id") Integer id) {
        AutoInStorageEntity inStorage = autoInStorageService.queryObject(id);

        return R.ok().put("inStorage", inStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("instorage:save")
    public R save(@RequestBody AutoInStorageEntity inStorage) {
        inStorage.setInType(2);
        autoInStorageService.save(inStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("instorage:update")
    public R update(@RequestBody AutoInStorageEntity inStorage) {
        autoInStorageService.update(inStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("instorage:delete")
    public R delete(@RequestBody Integer[] ids) {
        autoInStorageService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<AutoInStorageEntity> list = autoInStorageService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 审核通过的货物
     */
    @RequestMapping("/autoInput")
    public R autoInput(@RequestBody Integer[] ids){
        return R.ok();
    }

    /**
     * 同意入库
     */
    @RequestMapping("/agree/{id}")
    public R agreeInStorage(@PathVariable("id") Integer id){
        //根据传来的入库货物的id,查询其相关的信息
        AutoInStorageEntity autoInStorage=autoInStorageService.queryObject(id);
        String no=CommonUtil.generateOrderNumber();
        //插入入库信息表
        CurrentFoodEntity foodEntity=new CurrentFoodEntity();
        foodEntity.setCategoryId(autoInStorage.getCategoryId());
        foodEntity.setNo(no);
        foodEntity.setCustomerId(autoInStorage.getCustomerId());
        foodEntity.setNum(autoInStorage.getNum());
        foodEntity.setShelvesId(autoInStorage.getShelvesId());
        foodEntity.setShelvesNum(autoInStorage.getShelvesNum());
        foodEntity.setCheckId(autoInStorage.getCheckId());
        foodEntity.setInType(autoInStorage.getInType());
        foodEntity.setCheckTime(new Date());
        foodEntity.setInstorageTime(autoInStorage.getCreateTime());

        //增加到出入库明细表中
        StorageItemEntity storageItem=new StorageItemEntity();
        storageItem.setCategoryId(autoInStorage.getCategoryId());
        storageItem.setNo(no);
        storageItem.setCustomerId(autoInStorage.getCustomerId());
        storageItem.setNum(autoInStorage.getNum());
        storageItem.setShelvesId(autoInStorage.getShelvesId());
        storageItem.setShelvesNum(autoInStorage.getShelvesNum());
        storageItem.setCheckId(autoInStorage.getCheckId());
        storageItem.setInCheckTime(new Date());
        storageItem.setInStorageTime(autoInStorage.getCreateTime());

        //更新自动入库表
        autoInStorage.setStatus(1);
        //确定入库
        try{
            autoInStorageService.agreeInStorage(autoInStorage,foodEntity,storageItem);
        }catch(Exception e){
            e.printStackTrace();
            return R.ok("网络异常");
        }
        return R.ok();
    }

    /**
     * 拒绝入库
     */
    @RequestMapping("/unagree/{id}")
    public R unAgreeInStorage(@PathVariable("id") Integer id){
        //根据传来的入库货物的id,查询其相关的信息
        AutoInStorageEntity autoInStorage=autoInStorageService.queryObject(id);
        String no=CommonUtil.generateOrderNumber();
        //插入入库信息表

        //增加到出入库明细表中
        StorageItemEntity storageItem=new StorageItemEntity();
        storageItem.setCategoryId(autoInStorage.getCategoryId());
        storageItem.setNo(no);
        storageItem.setCustomerId(autoInStorage.getCustomerId());
        storageItem.setNum(autoInStorage.getNum());
        storageItem.setShelvesId(autoInStorage.getShelvesId());
        storageItem.setShelvesNum(autoInStorage.getShelvesNum());
        storageItem.setCheckId(autoInStorage.getCheckId());
        storageItem.setInCheckTime(new Date());
        storageItem.setInStorageTime(autoInStorage.getCreateTime());
        storageItem.setIsStorage(2);

        //更新自动入库表
        autoInStorage.setStatus(2);
        //确定入库
        try{
            autoInStorageService.unAgreeInStorage(autoInStorage,storageItem);
        }catch(Exception e){
            e.printStackTrace();
            return R.ok("网络异常");
        }
        return R.ok();
    }
}
