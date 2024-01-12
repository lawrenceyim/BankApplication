package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Business;
import com.solvd.bankapplication.persistence.BusinessDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BusinessDaoMybatisImpl implements BusinessDao {
    @Override
    public void create(Business business) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.create(business);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create business.", e);
        }
    }

    @Override
    public Optional<Business> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            return businessDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find business.", e);
        }
    }

    @Override
    public List<Business> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            return businessDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find business.", e);
        }
    }

    @Override
    public void update(Business business) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.update(business);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update business.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessDao businessDao = sqlSession.getMapper(BusinessDao.class);
            businessDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete business.", e);
        }
    }
}
