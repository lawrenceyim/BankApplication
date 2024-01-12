package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void create(Customer customer) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.create(customer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Customer> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            return customerDao.findById(id);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            return customerDao.findAll();
        }
    }

    @Override
    public void update(Customer customer) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.update(customer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteById(long id) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.deleteById(id);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
