package cn.smallshark.service.impl;

import cn.smallshark.dao.WarmDao;
import cn.smallshark.entity.WarmEntity;
import cn.smallshark.response.WarmVo;
import cn.smallshark.service.WarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2019-01-05 10:25:46
 */
@Service("warmService")
public class WarmServiceImpl implements WarmService {
    @Autowired
    private WarmDao warmDao;

    @Override
    public WarmEntity queryObject(Integer id) {
        return warmDao.queryObject(id);
    }

    @Override
    public List<WarmEntity> queryList(Map<String, Object> map) {
        return warmDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return warmDao.queryTotal(map);
    }

    @Override
    public int save(WarmEntity warm) {
        return warmDao.save(warm);
    }

    @Override
    public int update(WarmEntity warm) {
        return warmDao.update(warm);
    }

    @Override
    public int delete(Integer id) {
        return warmDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return warmDao.deleteBatch(ids);
    }

    @Override
    public List<WarmVo> queryAllNumGroupByWareHouse(){
        return warmDao.queryAllNumGroupByWareHouse();
    }

}
