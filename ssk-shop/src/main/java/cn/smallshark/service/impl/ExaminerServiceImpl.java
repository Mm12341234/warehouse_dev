package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.ExaminerDao;
import cn.smallshark.entity.ExaminerEntity;
import cn.smallshark.service.ExaminerService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-29 00:39:33
 */
@Service("examinerService")
public class ExaminerServiceImpl implements ExaminerService {
    @Autowired
    private ExaminerDao examinerDao;

    @Override
    public ExaminerEntity queryObject(Integer id) {
        return examinerDao.queryObject(id);
    }

    @Override
    public List<ExaminerEntity> queryList(Map<String, Object> map) {
        return examinerDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return examinerDao.queryTotal(map);
    }

    @Override
    public int save(ExaminerEntity examiner) {
        return examinerDao.save(examiner);
    }

    @Override
    public int update(ExaminerEntity examiner) {
        return examinerDao.update(examiner);
    }

    @Override
    public int delete(Integer id) {
        return examinerDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return examinerDao.deleteBatch(ids);
    }
}
