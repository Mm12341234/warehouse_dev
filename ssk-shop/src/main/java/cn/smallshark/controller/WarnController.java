package cn.smallshark.controller;


import cn.smallshark.service.WarnService;
import cn.smallshark.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "warn")
public class WarnController {
    @Autowired
    private WarnService warnService;

    @RequestMapping(value = "/count/{id}")
    @ResponseBody
    public R warnCount(@PathVariable("id") Integer id){
        int warnCount = warnService.queryWarnCount(id);
        return R.ok().put("warnCount",warnCount);
    }

}
