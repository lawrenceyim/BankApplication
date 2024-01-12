package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public void create(Payment payment) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.create(payment);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Payment> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findById(id);
        }
    }

    @Override
    public List<Payment> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findAll();
        }
    }

    @Override
    public List<Payment> findAllByCard(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findAllByCard(id);
        }
    }

    @Override
    public void update(Payment payment) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.update(payment);
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
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.deleteById(id);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
