package cn.smallshark.dao;

import cn.smallshark.entity.CommentVo;

import java.util.Map;

/**
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-08-11 09:14:26
 */
public interface ApiCommentMapper extends BaseDao<CommentVo> {
    int queryhasPicTotal(Map<String, Object> map);
}
