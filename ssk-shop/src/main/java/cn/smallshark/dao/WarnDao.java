package cn.smallshark.dao;

import cn.smallshark.entity.WarnEntity;

public interface WarnDao extends BaseDao<WarnEntity>{

    /**
     * 保存报警信息
     */
    public void save();

    /**
     * 获取警告的次数
     * @param foodId
     * @return
     */
    public int queryWarnCount(Integer foodId);
}
