package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CustomerRepositoryMybatisImpl implements CustomerRepository {

    @Override
    public void create(Customer customer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.create(customer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create customer.", e);
        }
    }

    @Override
    public Optional<Customer> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer.", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find customer.", e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.update(customer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update customer.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete customer.", e);
        }
    }
}
