package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BankDaoMybatisImpl implements BankDao {
    @Override
    public void create(Bank bank) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.create(bank);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create bank.", e);
        }
    }

    @Override
    public Optional<Bank> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            return bankDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find bank.", e);
        }
    }

    @Override
    public List<Bank> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            return bankDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find bank.", e);
        }
    }

    @Override
    public void update(Bank bank) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.update(bank);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update bank.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete bank.", e);
        }
    }
}
