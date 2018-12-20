package cn.smallshark.service.impl;

import cn.smallshark.dao.TestSysUserDao;
import cn.smallshark.entity.SysUserEntity;
import cn.smallshark.service.TestSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service测试实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-07-09 10:18:34
 */
@Service("testUserService")
public class TestSysUserServiceImpl implements TestSysUserService {
    @Autowired
    private TestSysUserDao testSysUserDao;

    @Override
    public SysUserEntity queryObject(Integer id) {
        return testSysUserDao.queryObject(id);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return testSysUserDao.queryList(map);
    }
}
