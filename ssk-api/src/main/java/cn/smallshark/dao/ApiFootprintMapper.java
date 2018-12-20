package cn.smallshark.dao;

import cn.smallshark.entity.FootprintVo;

import java.util.List;
import java.util.Map;

/**
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017-08-11 09:14:26
 */
public interface ApiFootprintMapper extends BaseDao<FootprintVo> {
    int deleteByParam(Map<String, Object> map);

    List<FootprintVo> shareList(Map<String, Object> map);

	List<FootprintVo> queryListFootprint(String userid);
}
