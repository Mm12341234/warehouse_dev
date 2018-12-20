package cn.smallshark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import cn.smallshark.dao.CustomerDao;
import cn.smallshark.entity.CustomerEntity;
import cn.smallshark.service.CustomerService;

/**
 * Service实现类
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2018-11-29 00:39:33
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerEntity queryObject(Integer id) {
        return customerDao.queryObject(id);
    }

    @Override
    public List<CustomerEntity> queryList(Map<String, Object> map) {
        return customerDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return customerDao.queryTotal(map);
    }

    @Override
    public int save(CustomerEntity customer) {
        return customerDao.save(customer);
    }

    @Override
    public int update(CustomerEntity customer) {
        return customerDao.update(customer);
    }

    @Override
    public int delete(Integer id) {
        return customerDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return customerDao.deleteBatch(ids);
    }
}
