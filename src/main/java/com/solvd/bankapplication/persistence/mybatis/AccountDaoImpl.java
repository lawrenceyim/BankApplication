package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void create(Account account) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            accountDao.create(account);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findById(id);
        }
    }

    @Override
    public List<Account> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findAll();
        }
    }

    @Override
    public List<Account> findByCustomer(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findByCustomer(id);
        }
    }

    @Override
    public void update(Account account) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            accountDao.update(account);
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
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            accountDao.deleteById(id);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
