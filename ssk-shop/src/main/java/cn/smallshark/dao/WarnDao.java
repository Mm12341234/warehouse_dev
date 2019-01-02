package cn.smallshark.dao;

import cn.smallshark.entity.WarnEntity;

public interface WarnDao extends BaseDao<WarnEntity>{

    /**
     * 保存报警信息
     */
    public void save();
}
