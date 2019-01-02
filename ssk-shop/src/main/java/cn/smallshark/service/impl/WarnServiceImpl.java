package cn.smallshark.service.impl;

import cn.smallshark.dao.WarnDao;
import cn.smallshark.entity.WarnEntity;
import cn.smallshark.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("warnService")
public class WarnServiceImpl implements WarnService {

    @Autowired
    private WarnDao warnDao;

    @Override
    public void save(WarnEntity warnEntity) {
        warnDao.save(warnEntity);
    }
}
