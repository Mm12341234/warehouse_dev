package cn.smallshark.controller;

import cn.smallshark.entity.WarmEntity;
import cn.smallshark.response.WarmVo;
import cn.smallshark.service.WarmService;
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
 * @date 2019-01-05 10:25:46
 */
@RestController
@RequestMapping("warm")
public class WarmController {
    @Autowired
    private WarmService warmService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("warm:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<WarmEntity> warmList = warmService.queryList(query);
        int total = warmService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(warmList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("warm:info")
    public R info(@PathVariable("id") Integer id) {
        WarmEntity warm = warmService.queryObject(id);

        return R.ok().put("warm", warm);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("warm:save")
    public R save(@RequestBody WarmEntity warm) {
        warmService.save(warm);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("warm:update")
    public R update(@RequestBody WarmEntity warm) {
        warmService.update(warm);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("warm:delete")
    public R delete(@RequestBody Integer[] ids) {
        warmService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<WarmEntity> list = warmService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据不同的仓库来统计各个仓库的总数
     */
    @RequestMapping("/queryAllNumGroupByWareHouse")
    public R queryAllNumGroupByWareHouse(){
        List<WarmVo> list=warmService.queryAllNumGroupByWareHouse();
        return R.ok().put("list",list);
    }
}
