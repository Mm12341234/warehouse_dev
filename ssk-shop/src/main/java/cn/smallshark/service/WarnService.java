package cn.smallshark.service;

import cn.smallshark.entity.WarnEntity;

/**
 * 报警接口
 */
public interface WarnService {

    /**
     * 保存报警信息
     */
    public void save(WarnEntity warnEntity);

}
