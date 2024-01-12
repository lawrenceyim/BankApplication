package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.CustomerLoginDetail;
import com.solvd.bankapplication.persistence.CustomerLoginDetailDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerLoginDetailDaoImpl implements CustomerLoginDetailDao {
    @Override
    public void create(CustomerLoginDetail customerLoginDetail) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.create(customerLoginDetail);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<CustomerLoginDetail> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            return customerLoginDetailDao.findById(id);
        }
    }

    @Override
    public List<CustomerLoginDetail> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            return customerLoginDetailDao.findAll();
        }
    }

    @Override
    public void update(CustomerLoginDetail customerLoginDetail) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.update(customerLoginDetail);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteById(long id) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerLoginDetailDao customerLoginDetailDao = sqlSession.getMapper(CustomerLoginDetailDao.class);
            customerLoginDetailDao.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
