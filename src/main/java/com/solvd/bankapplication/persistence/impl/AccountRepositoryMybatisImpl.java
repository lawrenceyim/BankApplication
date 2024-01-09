package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountRepository;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryMybatisImpl implements AccountRepository {
    @Override
    public void create(Account account) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.create(account);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create account.", e);
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public List<Account> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public List<Account> findByCustomer(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.findByCustomer(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find account.", e);
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.update(account);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update account.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete account.", e);
        }
    }
}
