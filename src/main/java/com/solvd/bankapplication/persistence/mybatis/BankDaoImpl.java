package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BankDaoImpl implements BankDao {
    @Override
    public void create(Bank bank) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.create(bank);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Bank> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            return bankDao.findById(id);
        }
    }

    @Override
    public List<Bank> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            return bankDao.findAll();
        }
    }

    @Override
    public void update(Bank bank) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.update(bank);
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
            BankDao bankDao = sqlSession.getMapper(BankDao.class);
            bankDao.deleteById(id);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
