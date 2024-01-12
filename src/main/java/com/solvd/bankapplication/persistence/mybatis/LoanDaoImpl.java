package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Loan;
import com.solvd.bankapplication.persistence.LoanDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class LoanDaoImpl implements LoanDao {

    @Override
    public void create(Loan loan) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.create(loan);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Loan> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            return loanDao.findById(id);
        }
    }

    @Override
    public List<Loan> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            return loanDao.findAll();
        }
    }

    @Override
    public void update(Loan loan) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.update(loan);
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
            LoanDao loanDao = sqlSession.getMapper(LoanDao.class);
            loanDao.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
