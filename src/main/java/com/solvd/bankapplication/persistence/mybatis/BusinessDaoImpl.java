package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Business;
import com.solvd.bankapplication.persistence.BusinessDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BusinessDaoImpl implements BusinessDao {
    @Override
    public void create(Business business) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.create(business);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Business> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            return businessDao.findById(id);
        }
    }

    @Override
    public List<Business> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            return businessDao.findAll();
        }
    }

    @Override
    public void update(Business business) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.update(business);
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
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.deleteById(id);
            sqlSession.commit();
        } catch (PersistenceException e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
