package cn.smallshark.controller;

import cn.smallshark.entity.CurrentFoodEntity;
import cn.smallshark.entity.FoodtemperatureEntity;
import cn.smallshark.entity.SuitableTemperatureEntity;
import cn.smallshark.entity.WarnEntity;
import cn.smallshark.service.*;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:57:09
 */
@RestController
@RequestMapping("foodtemperature")
public class FoodtemperatureController {
    @Autowired
    private FoodtemperatureService foodtemperatureService;
    @Autowired
    private SuitableTemperatureService suitableTemperatureService;
    @Autowired
    private FoodCategoryService foodCategoryService;
    @Autowired
    private CurrentFoodService currentFoodService;
    @Autowired
    private WarnService warnService;

    //插入自定义的温度，测试温度报警
    @ResponseBody
    @PostMapping("/Foods/insertFoodTemperature")
    @Async
    public int insertFoodTemperature(@RequestParam("foodTemperature")double temperature){
        int flag = 1;
        FoodtemperatureEntity foodTemperature = new FoodtemperatureEntity();
        foodTemperature.setFoodid(2);
        foodTemperature.setFoodtemperature(temperature);
        foodTemperature.setTemperaturetime(new Date());
        foodtemperatureService.save(foodTemperature);
        return flag;
    }

    //从后台异步加载,获取最新的温度和时间
    @RequestMapping(value = "/getNewTemperature/{foodId}")
    @ResponseBody
    public String getNewFoodTemperature(@PathVariable("foodId") Integer foodId) throws Exception {
        //1. 根据查询食品id，获取食品的温度对象
        FoodtemperatureEntity foodTemperature = foodtemperatureService.queryNewFoodTemperatureById(foodId);
        //根据食品id，查询食品对象，获取对应的类别id。
        CurrentFoodEntity currentFood = currentFoodService.queryObject(foodId);
        if(currentFood != null){
            Integer categoryId = currentFood.getCategoryId();
            //2. 根据食品类别id，查询食品的最佳存储温度范围
            SuitableTemperatureEntity suitableTemperatureEntity = suitableTemperatureService.queryObject(categoryId);
            if(suitableTemperatureEntity != null){
                Double minTemperature = suitableTemperatureEntity.getMinTemperature(); // 最小存储温度
                Double maxTemperature = suitableTemperatureEntity.getMaxTemperature(); // 最大存储温度
                //3. 检查温度是否超出食品最佳存储温度范围
                if(foodTemperature != null){
                    double temperature = foodTemperature.getFoodtemperature();
                    if(temperature > maxTemperature || temperature < minTemperature){
                        //4. 调用邮箱接口，发送温度报警信息（邮箱只是进行模拟，实际应用中可使用短信报警）
                        //① 填写邮件用户，标题，内容
                        //String usereamil = "17876253361@163.com";
                        //String eamiltitle = "温度报警";
                        //String msg = foodTemperature.getFoodid() + "现在的温度是" + temperature + "超出合适的存储温度！请马上处理！";
                        //② 发送电子邮件
                        //sendEmail.sendAttachmentMaik(usereamil,eamiltitle,msg);
                        //System.out.println("发送邮件完成！");
                        //5. 判断该报警信息是温度报警，将报警信息保存到表中
                        System.out.println("当前温度为：" + temperature + "出现异常了！");
                        WarnEntity warn = new WarnEntity();
                        String uuid = UUID.randomUUID().toString().replaceAll("-","");
                        warn.setId(uuid);
                        warn.setCurrentFoodId(foodId);
                        // 报警类型是温度报警
                        warn.setType(2);
                        // 当前时间，格式化
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-MM hh:mm:ss");
                        warn.setTime(new Date());
                        warn.setShelvesId(currentFood.getShelvesId());
                        warn.setRoomId(currentFood.getRoomId());
                        warn.setWarehouseId(currentFood.getWarehouseId());
                        // 备注信息，报警相关的信息(可以是短信的内容，这里为空字符串)
                        String remark = " ";
                        warn.setRemark(remark);
                        // 保存
                        warnService.save(warn);
                    }
                }
            }
        }
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ObjectMapper mapper =new ObjectMapper();
        mapper.getSerializationConfig().with(formatter);
        String jsonlist = mapper.writeValueAsString(foodTemperature);
        System.out.println(jsonlist);
        return jsonlist;
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("foodtemperature:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FoodtemperatureEntity> foodtemperatureList = foodtemperatureService.queryList(query);
        int total = foodtemperatureService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(foodtemperatureList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("foodtemperature:info")
    public R info(@PathVariable("id") Integer id) {
        FoodtemperatureEntity foodtemperature = foodtemperatureService.queryObject(id);

        return R.ok().put("foodtemperature", foodtemperature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("foodtemperature:save")
    public R save(@RequestBody FoodtemperatureEntity foodtemperature) {
        foodtemperatureService.save(foodtemperature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("foodtemperature:update")
    public R update(@RequestBody FoodtemperatureEntity foodtemperature) {
        foodtemperatureService.update(foodtemperature);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("foodtemperature:delete")
    public R delete(@RequestBody Integer[] ids) {
        foodtemperatureService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<FoodtemperatureEntity> list = foodtemperatureService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取食物的温度
     */
    @RequestMapping("/queryTemperature1/{id}")
    public R queryTemperature(@PathVariable("id") Integer id){
        List<FoodtemperatureEntity> list = foodtemperatureService.queryTemperature(id);
        return R.ok().put("list",list);
    }

    @RequestMapping("/addTemperature")
    public void addTemperature(){
        System.out.println("你好");
    }
}
