package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerDaoMybatisImpl implements CustomerDao {

    @Override
    public void create(Customer customer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.create(customer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create customer.", e);
        }
    }

    @Override
    public Optional<Customer> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            return customerDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer.", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            return customerDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer.", e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.update(customer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
            customerDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete customer.", e);
        }
    }
}
