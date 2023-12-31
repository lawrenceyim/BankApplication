package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.persistence.PaymentRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PaymentRepositoryMybatisImpl implements PaymentRepository {

    @Override
    public void create(Payment payment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            paymentRepository.create(payment);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create payment.", e);
        }
    }

    @Override
    public Optional<Payment> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            return paymentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public List<Payment> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            return paymentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public List<Payment> findAllByCard(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            return paymentRepository.findAllByCard(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find payment.", e);
        }
    }

    @Override
    public void update(Payment payment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            paymentRepository.update(payment);
        } catch (Exception e) {
            throw new RuntimeException("Unable to payment payment.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            paymentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete payment.", e);
        }
    }
}
