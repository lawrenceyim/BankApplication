package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Individual;
import com.solvd.bankapplication.persistence.IndividualDao;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class IndividualDaoImpl implements IndividualDao {
    @Override
    public void create(Individual individual) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.create(individual);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create individual.", e);
        }
    }

    @Override
    public Optional<Individual> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            return individualDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find individual.", e);
        }
    }

    @Override
    public List<Individual> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            return individualDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find individual.", e);
        }
    }

    @Override
    public void update(Individual individual) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.update(individual);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update individual.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualDao individualDao = sqlSession.getMapper(IndividualDao.class);
            individualDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete individual.", e);
        }
    }
}
