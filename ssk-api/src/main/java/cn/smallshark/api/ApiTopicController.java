package cn.smallshark.api;

import cn.smallshark.annotation.IgnoreAuth;
import cn.smallshark.annotation.LoginUser;
import cn.smallshark.entity.TopicVo;
import cn.smallshark.entity.UserVo;
import cn.smallshark.service.ApiTopicService;
import cn.smallshark.util.ApiBaseAction;
import cn.smallshark.util.ApiPageUtils;
import cn.smallshark.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author feking.fang <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@RestController
@RequestMapping("/api/topic")
public class ApiTopicController extends ApiBaseAction {
    @Autowired
    private ApiTopicService topicService;

    /**
     */
    @IgnoreAuth
    @GetMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map param = new HashMap();
        param.put("page", page);
        param.put("limit", size);
        param.put("sidx", "id");
        param.put("order", "desc");
        param.put("fields", "id, title, price_info, scene_pic_url,subtitle");
        //查询列表数据
        Query query = new Query(param);
        List<TopicVo> topicEntities = topicService.queryList(query);
        int total = topicService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(topicEntities, total, query.getLimit(), query.getPage());
        return toResponsSuccess(pageUtil);
    }

    /**
     */
    @IgnoreAuth
    @GetMapping("detail")
    public Object detail(@LoginUser UserVo loginUser, Integer id) {
        TopicVo topicEntity = topicService.queryObject(id);
        return toResponsSuccess(topicEntity);
    }

    /**
     */
    @IgnoreAuth
    @GetMapping("related")
    public Object related(@LoginUser UserVo loginUser, Integer id) {
        Map param = new HashMap();
        param.put("limit", 4);
        List<TopicVo> topicEntities = topicService.queryList(param);
        return toResponsSuccess(topicEntities);
    }
}