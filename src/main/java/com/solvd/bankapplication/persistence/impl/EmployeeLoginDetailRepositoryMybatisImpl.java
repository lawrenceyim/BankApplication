package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.EmployeeLoginDetail;
import com.solvd.bankapplication.persistence.EmployeeLoginDetailRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class EmployeeLoginDetailRepositoryMybatisImpl implements EmployeeLoginDetailRepository {
    @Override
    public void create(EmployeeLoginDetail employeeLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeLoginDetailRepository employeeLoginDetailRepository = sqlSession.getMapper(EmployeeLoginDetailRepository.class);
            employeeLoginDetailRepository.create(employeeLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create employee login detail.", e);
        }
    }

    @Override
    public Optional<EmployeeLoginDetail> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeLoginDetailRepository employeeLoginDetailRepository = sqlSession.getMapper(EmployeeLoginDetailRepository.class);
            return employeeLoginDetailRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find employee login detail.", e);
        }
    }

    @Override
    public List<EmployeeLoginDetail> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeLoginDetailRepository employeeLoginDetailRepository = sqlSession.getMapper(EmployeeLoginDetailRepository.class);
            return employeeLoginDetailRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find employee login detail.", e);
        }
    }

    @Override
    public void update(EmployeeLoginDetail employeeLoginDetail) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeLoginDetailRepository employeeLoginDetailRepository = sqlSession.getMapper(EmployeeLoginDetailRepository.class);
            employeeLoginDetailRepository.update(employeeLoginDetail);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update employee login detail.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeLoginDetailRepository employeeLoginDetailRepository = sqlSession.getMapper(EmployeeLoginDetailRepository.class);
            employeeLoginDetailRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete employee login detail.", e);
        }
    }
}
