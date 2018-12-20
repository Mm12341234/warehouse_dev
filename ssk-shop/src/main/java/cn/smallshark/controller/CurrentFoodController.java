package cn.smallshark.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import cn.smallshark.utils.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.service.CurrentFoodService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
@RestController
@RequestMapping("currentfood")
public class CurrentFoodController {
    @Autowired
    private CurrentFoodService currentFoodService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("currentfood:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CurrentFoodEntity> currentFoodList = currentFoodService.queryList(query);
        int total = currentFoodService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(currentFoodList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("currentfood:info")
    public R info(@PathVariable("id") Integer id) {
        CurrentFoodEntity currentFood = currentFoodService.queryObject(id);

        return R.ok().put("currentFood", currentFood);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("currentfood:save")
    public R save(@RequestBody CurrentFoodEntity currentFood) {
        currentFoodService.save(currentFood);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("currentfood:update")
    public R update(@RequestBody CurrentFoodEntity currentFood) {
        currentFoodService.update(currentFood);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("currentfood:delete")
    public R delete(@PathVariable("id") Integer id) {
        currentFoodService.delete(id);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CurrentFoodEntity> list = currentFoodService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 将完成订单记录导出到excel表1
     */
    @RequestMapping("/excelexport")
    public void excelexport(HttpServletResponse response, HttpServletRequest request) {

//        1、使用JSONObject获取订单详情对象（从JSON到对象）
        String name=request.getParameter("sod");

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",name);

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String today = null;
        today = sdf.format(dt);
        ExcelExport ee1 = new ExcelExport("调查员"+ today);
        List<Object[]> alllist = new ArrayList();
        List<CurrentFoodEntity> list = currentFoodService.queryList(map);
        List colList;

        for (CurrentFoodEntity entity : list) {
            colList = new ArrayList();
            colList.add(entity.getId());
            colList.add(entity.getInType());
            alllist.add(colList.toArray());
        }
        String[] header = new String[]{"调查员编号", "姓名", "手机号码","行业经验","熟悉的货物",
                "了解程度", "交通工具", "学历", "常住地区", "详细地址", "身份证号码", "证件类型"};
        ee1.addSheetByArray("所有记录", alllist, header);
        ee1.export(response);
    }

    /**
     * 获
     */
}
