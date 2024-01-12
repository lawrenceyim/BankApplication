package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class TransferDaoImpl implements TransferDao {
    @Override
    public void create(Transfer transfer) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.create(transfer);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Transfer> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findById(id);
        }
    }

    @Override
    public List<Transfer> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findAll();
        }
    }

    @Override
    public List<Transfer> findAllByAccount(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            return transferDao.findAllByAccount(id);
        }
    }

    @Override
    public void update(Transfer transfer) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.update(transfer);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteById(long id) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            TransferDao transferDao = sqlSession.getMapper(TransferDao.class);
            transferDao.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
