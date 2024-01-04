package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Employee;
import com.solvd.bankapplication.persistence.EmployeeRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryMybatisImpl implements EmployeeRepository {

    @Override
    public void create(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create employee.", e);
        }
    }

    @Override
    public Optional<Employee> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find employee.", e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find employee.", e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.update(employee);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update employee.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete employee.", e);
        }
    }
}
