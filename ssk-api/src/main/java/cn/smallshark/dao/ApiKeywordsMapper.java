package cn.smallshark.dao;

import cn.smallshark.entity.KeywordsVo;

import java.util.List;
import java.util.Map;

/**
 * 热闹关键词表
 * 
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-08-11 09:16:46
 */
public interface ApiKeywordsMapper extends BaseDao<KeywordsVo> {
    List<Map> hotKeywordList(Map param);
}
