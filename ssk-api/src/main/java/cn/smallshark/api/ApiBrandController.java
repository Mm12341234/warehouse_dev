package cn.smallshark.api;

import cn.smallshark.annotation.IgnoreAuth;
import cn.smallshark.entity.BrandVo;
import cn.smallshark.service.ApiBrandService;
import cn.smallshark.util.ApiBaseAction;
import cn.smallshark.util.ApiPageUtils;
import cn.smallshark.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class ApiBrandController extends ApiBaseAction {
    @Autowired
    private ApiBrandService brandService;

    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @IgnoreAuth
    @GetMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //查询列表数据
        Map params = new HashMap();
        params.put("fields", "id, name, floor_price, app_list_pic_url");
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "id");
        params.put("order", "asc");

        Query query = new Query(params);
        List<BrandVo> brandEntityList = brandService.queryList(query);
        int total = brandService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(brandEntityList, total, query.getLimit(), query.getPage());
        //
        return toResponsSuccess(pageUtil);
    }

    /**
     * 品牌详情
     */
    @ApiOperation(value = "品牌详情")
    @IgnoreAuth
    @GetMapping("detail")
    public Object detail(@RequestParam Integer id) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        BrandVo entity = brandService.queryObject(id);
        //
        resultObj.put("brand", entity);
        return toResponsSuccess(resultObj);
    }
}