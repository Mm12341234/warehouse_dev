package cn.smallshark.dao;

import cn.smallshark.entity.SysRoleEntity;
import cn.smallshark.entity.UserWindowDto;

import java.util.List;

/**
 * 角色管理
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2016年9月18日 上午9:33:33
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

    /**
     * 查询角色审批选择范围
     * @return
     */
    List<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto);
}
