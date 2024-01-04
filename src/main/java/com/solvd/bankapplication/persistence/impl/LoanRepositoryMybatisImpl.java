package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Loan;
import com.solvd.bankapplication.persistence.LoanRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class LoanRepositoryMybatisImpl implements LoanRepository {

    @Override
    public void create(Loan loan) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.create(loan);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create loan.", e);
        }
    }

    @Override
    public Optional<Loan> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            return loanRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find loan.", e);
        }
    }

    @Override
    public List<Loan> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            return loanRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find loan.", e);
        }
    }

    @Override
    public void update(Loan loan) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.update(loan);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update loan.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanRepository loanRepository = sqlSession.getMapper(LoanRepository.class);
            loanRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete loan.", e);
        }
    }
}
