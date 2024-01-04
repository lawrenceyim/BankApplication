package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Business;
import com.solvd.bankapplication.persistence.BusinessRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BusinessRepositoryMybatisImpl implements BusinessRepository {
    @Override
    public void create(Business business) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessRepository businessRepository = sqlSession.getMapper(BusinessRepository.class);
            businessRepository.create(business);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create business.", e);
        }
    }

    @Override
    public Optional<Business> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessRepository businessRepository = sqlSession.getMapper(BusinessRepository.class);
            return businessRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find business.", e);
        }
    }

    @Override
    public List<Business> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessRepository businessRepository = sqlSession.getMapper(BusinessRepository.class);
            return businessRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find business.", e);
        }
    }

    @Override
    public void update(Business business) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessRepository businessRepository = sqlSession.getMapper(BusinessRepository.class);
            businessRepository.update(business);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update business.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BusinessRepository businessRepository = sqlSession.getMapper(BusinessRepository.class);
            businessRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete business.", e);
        }
    }
}
