package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Loan;
import com.solvd.bankapplication.persistence.LoanDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class LoanDaoImpl implements LoanDao {

    @Override
    public void create(Loan loan) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.create(loan);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create loan.", e);
        }
    }

    @Override
    public Optional<Loan> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            return loanDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find loan.", e);
        }
    }

    @Override
    public List<Loan> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            return loanDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find loan.", e);
        }
    }

    @Override
    public void update(Loan loan) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.update(loan);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update loan.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete loan.", e);
        }
    }
}
