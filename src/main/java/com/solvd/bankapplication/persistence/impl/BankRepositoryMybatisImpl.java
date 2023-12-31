package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BankRepositoryMybatisImpl implements BankRepository {
    @Override
    public void create(Bank bank) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.create(bank);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create bank.", e);
        }
    }

    @Override
    public Optional<Bank> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            return bankRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find bank.", e);
        }
    }

    @Override
    public List<Bank> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            return bankRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find bank.", e);
        }
    }

    @Override
    public void update(Bank bank) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.update(bank);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update bank.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete bank.", e);
        }
    }
}
