package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

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
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException("Unable to create account.", e);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public List<Account> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public List<Account> findByCustomer(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            return accountDao.findByCustomer(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            accountDao.update(account);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update account.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            accountDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete account.", e);
        }
    }
}
