package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public void create(Payment payment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.create(payment);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create payment.", e);
        }
    }

    @Override
    public Optional<Payment> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public List<Payment> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public List<Payment> findAllByCard(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            return paymentDao.findAllByCard(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public void update(Payment payment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.update(payment);
        } catch (Exception e) {
            throw new RuntimeException("Unable to payment payment.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentDao paymentDao = sqlSession.getMapper(PaymentDao.class);
            paymentDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete payment.", e);
        }
    }
}
