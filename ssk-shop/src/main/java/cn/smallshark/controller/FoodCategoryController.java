package cn.smallshark.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import cn.smallshark.entity.CategoryVo;
import cn.smallshark.utils.RRException;
import cn.smallshark.utils.excel.ExcelExport;
import cn.smallshark.utils.excel.ExcelImport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.FoodCategoryEntity;
import cn.smallshark.service.FoodCategoryService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-12-03 01:00:25
 */
@RestController
@RequestMapping("foodcategory")
public class FoodCategoryController {
    @Autowired
    private FoodCategoryService foodCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodcategory:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodCategoryEntity> foodCategoryList = foodCategoryService.queryList(query);
        int total = foodCategoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodCategoryList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看所有数据
     */
    @RequestMapping("/queryAllList")
    public R queryAll() {
        List<FoodCategoryEntity> foodCategoryList = foodCategoryService.queryAllList();
        return R.ok().put("list", foodCategoryList);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodcategory:info")
    public R info(@PathVariable("id") Integer id) {
        FoodCategoryEntity foodCategory = foodCategoryService.queryObject(id);

        return R.ok().put("foodCategory", foodCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodcategory:save")
    public R save(@RequestBody FoodCategoryEntity foodCategory) {
        foodCategoryService.save(foodCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodcategory:update")
    public R update(@RequestBody FoodCategoryEntity foodCategory) {
        foodCategoryService.update(foodCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodcategory:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodCategoryService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodCategoryEntity> list = foodCategoryService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据分类的等级查询所有类别
     */
    @RequestMapping("/queryCateByLevel")
//    public R update(@RequestBody Map<String,Object> params){
    public R queryCateByLevel(@RequestBody Map<String,Object> params){
        List<CategoryVo> list=foodCategoryService.queryCateByLevel(params);
        return R.ok().put("list",list);
    }

    /**
     * 根据父节点查询所有类别
     */
    @RequestMapping("/queryCateByParentId")
    public R queryCateByParentId(@RequestBody Map<String,Object> params){
        List<CategoryVo> list=foodCategoryService.queryCateByParentId(params);
        return R.ok().put("list",list);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return R
     * @throws Exception 异常
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        List<String[]> list= ExcelImport.getExcelData07(file);
        Iterator iterator = list.iterator();

        Integer parentOneId=0; //存放的是一级分类的ID
        Integer parentTwoId=1; //存放的是二级分类的ID

        while (iterator.hasNext()) {
            String[] list1 = (String [])iterator.next();
            for(int i=0;i<list1.length;i++){
                if(list1[i]!="") {
                    FoodCategoryEntity category=new FoodCategoryEntity();
                    if(i==0){
                        category.setName(list1[i]);
                        category.setParentId(0);
                        category.setLevel(1);
                        foodCategoryService.save(category);
                        parentOneId=category.getId();
                    }
                    if(i==1){
                        category.setName(list1[i]);
                        category.setParentId(parentOneId);
                        category.setLevel(2);
                        foodCategoryService.save(category);
                        parentTwoId=category.getId();
                    }
                    if(i==2){
                        category.setName(list1[i]);
                        category.setParentId(parentTwoId);
                        category.setLevel(3);
                        foodCategoryService.save(category);
                    }
                }
            }
        }
        return R.ok("导入成功");
    }

    //下载模板
    @RequestMapping("/excelexport")
    public void load(HttpServletResponse response){

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String today = null;
        today = sdf.format(dt);
        ExcelExport ee1 = new ExcelExport("导入文件模板"+ today);
        List<Object[]> alllist = new ArrayList();
        List colList;

        for (int i=1;i<5;i++) {
            colList = new ArrayList();
            if(i==0){
                colList.add("一级分类");
                colList.add("");
                colList.add("");
            }else if(i==1){
                colList.add("");
                colList.add("二级分类");
                colList.add("");
            }
            else if(i==2){
                colList.add("");
                colList.add("二级分类");
                colList.add("");
            }else if(i==3){
                colList.add("");
                colList.add("");
                colList.add("三级分类");
            }else{
                colList.add("一级分类");
                colList.add("");
                colList.add("");
            }
            alllist.add(colList.toArray());
        }
        String[] header = new String[]{"一级分类", "", ""};
        ee1.addSheetByArray("所有记录", alllist, header);
        ee1.export(response);
    }

}
