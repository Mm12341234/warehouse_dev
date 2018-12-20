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

import cn.smallshark.entity.ExaminerEntity;
import cn.smallshark.service.ExaminerService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-29 00:39:33
 */
@RestController
@RequestMapping("examiner")
public class ExaminerController {
    @Autowired
    private ExaminerService examinerService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("examiner:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ExaminerEntity> examinerList = examinerService.queryList(query);
        int total = examinerService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(examinerList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("examiner:info")
    public R info(@PathVariable("id") Integer id) {
        ExaminerEntity examiner = examinerService.queryObject(id);

        return R.ok().put("examiner", examiner);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("examiner:save")
    public R save(@RequestBody ExaminerEntity examiner) {
        examinerService.save(examiner);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("examiner:update")
    public R update(@RequestBody ExaminerEntity examiner) {
        examinerService.update(examiner);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("examiner:delete")
    public R delete(@RequestBody Integer[] ids) {
        examinerService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ExaminerEntity> list = examinerService.queryList(params);

        return R.ok().put("list", list);
    }
}
