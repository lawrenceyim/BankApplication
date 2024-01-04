package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.CustomerLoginDetail;
import com.solvd.bankapplication.persistence.CustomerLoginDetailRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerLoginDetailRepositoryMybatisImpl implements CustomerLoginDetailRepository {
    @Override
    public void create(CustomerLoginDetail customerLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailRepository customerLoginDetailRepository = sqlSession.getMapper(CustomerLoginDetailRepository.class);
            customerLoginDetailRepository.create(customerLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create customer login detail.", e);
        }
    }

    @Override
    public Optional<CustomerLoginDetail> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailRepository customerLoginDetailRepository = sqlSession.getMapper(CustomerLoginDetailRepository.class);
            return customerLoginDetailRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        }
    }

    @Override
    public List<CustomerLoginDetail> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailRepository customerLoginDetailRepository = sqlSession.getMapper(CustomerLoginDetailRepository.class);
            return customerLoginDetailRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        }
    }

    @Override
    public void update(CustomerLoginDetail customerLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailRepository customerLoginDetailRepository = sqlSession.getMapper(CustomerLoginDetailRepository.class);
            customerLoginDetailRepository.update(customerLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer login detail.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerLoginDetailRepository customerLoginDetailRepository = sqlSession.getMapper(CustomerLoginDetailRepository.class);
            customerLoginDetailRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer login detail.", e);
        }
    }
}
