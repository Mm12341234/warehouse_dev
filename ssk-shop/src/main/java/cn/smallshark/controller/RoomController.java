package cn.smallshark.controller;

import java.util.List;
import java.util.Map;

import cn.smallshark.entity.RoomVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.smallshark.entity.RoomEntity;
import cn.smallshark.service.RoomService;
import cn.smallshark.utils.PageUtils;
import cn.smallshark.utils.Query;
import cn.smallshark.utils.R;

/**
 * Controller
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-26 20:56:26
 */
@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("room:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<RoomEntity> roomList = roomService.queryList(query);
        int total = roomService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(roomList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("room:info")
    public R info(@PathVariable("id") String id) {
        RoomEntity room = roomService.queryObject(id);

        return R.ok().put("room", room);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("room:save")
    public R save(@RequestBody RoomEntity room) {
        roomService.save(room);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("room:update")
    public R update(@RequestBody RoomEntity room) {
        roomService.update(room);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("room:delete")
    public R delete(@RequestBody String[] ids) {
        roomService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<RoomEntity> list = roomService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/queryRoomlist")
    public R queryRoomlist(@RequestBody Map<String, Object> params) {

        List<RoomEntity> list = roomService.queryList(params);
        return R.ok().put("list", list);
    }

    @RequestMapping("/queryRoomVo/{id}")
    public R queryRoomVo(@PathVariable("id") Integer id) {

        List<RoomVo> list = roomService.queryRoomVo(id);
        return R.ok().put("list", list);
    }
}
