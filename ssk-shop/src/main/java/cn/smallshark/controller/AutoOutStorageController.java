package cn.smallshark.controller;

import cn.smallshark.entity.AutoOutStorageEntity;
import cn.smallshark.service.AutoOutStorageService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;
import cn.smallshark.utils.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:25
 */
@RestController
@RequestMapping("autooutstorage")
public class AutoOutStorageController {
    @Autowired
    private AutoOutStorageService autoOutstorageService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("autooutstorage:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AutoOutStorageEntity> outstorageList = autoOutstorageService.queryList(query);
        int total = autoOutstorageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(outstorageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("autooutstorage:info")
    public R info(@PathVariable("id") Integer id) {
        AutoOutStorageEntity outstorage = autoOutstorageService.queryObject(id);

        return R.ok().put("outstorage", outstorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("autooutstorage:save")
    public R save(@RequestBody AutoOutStorageEntity outstorage) {
        autoOutstorageService.save(outstorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("autooutstorage:update")
    public R update(@RequestBody AutoOutStorageEntity autooutstorage) {
        autoOutstorageService.update(autooutstorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("autooutstorage:delete")
    public R delete(@RequestBody Integer[] ids) {
        autoOutstorageService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<AutoOutStorageEntity> list = autoOutstorageService.queryList(params);

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
        ExcelExport ee1 = new ExcelExport("自动出库表"+ today);
        List<Object[]> alllist = new ArrayList();
        List<AutoOutStorageEntity> list = autoOutstorageService.queryList(map);
        List colList;

        for (AutoOutStorageEntity entity : list) {
            colList = new ArrayList();
            colList.add(entity.getId());
            colList.add(entity.getFinishTime());
            colList.add(entity.getInType());
            colList.add(entity.getCategoryId());
            alllist.add(colList.toArray());
        }
        String[] header = new String[]{"调查员编号", "姓名", "手机号码","行业经验","熟悉的货物",
                "了解程度", "交通工具", "学历", "常住地区", "详细地址", "身份证号码", "证件类型"};
        ee1.addSheetByArray("所有记录", alllist, header);
        ee1.export(response);
    }
}
