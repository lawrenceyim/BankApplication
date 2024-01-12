package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class TransferDaoMybatisImpl implements TransferDao {
    @Override
    public void create(Transfer transfer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.create(transfer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create transfer.", e);
        }
    }

    @Override
    public Optional<Transfer> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find transfer.", e);
        }
    }

    @Override
    public List<Transfer> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find transfer.", e);
        }
    }

    @Override
    public List<Transfer> findAllByAccount(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findAllByAccount(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find transfer.", e);
        }
    }

    @Override
    public void update(Transfer transfer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.update(transfer);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update transfer.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete transfer.", e);
        }
    }
}
