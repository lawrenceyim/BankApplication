package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.CustomerLoginDetail;
import com.solvd.bankapplication.persistence.CustomerLoginDetailDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerLoginDetailDaoMybatisImpl implements CustomerLoginDetailDao {
    @Override
    public void create(CustomerLoginDetail customerLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.create(customerLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create customer login detail.", e);
        }
    }

    @Override
    public Optional<CustomerLoginDetail> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            return customerLoginDetailDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        }
    }

    @Override
    public List<CustomerLoginDetail> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            return customerLoginDetailDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        }
    }

    @Override
    public void update(CustomerLoginDetail customerLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.update(customerLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer login detail.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer login detail.", e);
        }
    }
}
