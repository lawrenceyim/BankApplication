package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Individual;
import com.solvd.bankapplication.persistence.IndividualDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class IndividualDaoImpl implements IndividualDao {
    @Override
    public void create(Individual individual) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.create(individual);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Individual> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            return individualDao.findById(id);
        }
    }

    @Override
    public List<Individual> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            return individualDao.findAll();
        }
    }

    @Override
    public void update(Individual individual) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.update(individual);
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
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
