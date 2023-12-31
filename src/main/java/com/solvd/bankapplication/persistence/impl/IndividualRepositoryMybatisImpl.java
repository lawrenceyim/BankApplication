package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Individual;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.persistence.IndividualRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class IndividualRepositoryMybatisImpl implements IndividualRepository {
    @Override
    public void create(Individual individual) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualRepository individualRepository = sqlSession.getMapper(IndividualRepository.class);
            individualRepository.create(individual);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create individual.", e);
        }
    }

    @Override
    public Optional<Individual> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualRepository individualRepository = sqlSession.getMapper(IndividualRepository.class);
            return individualRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find individual.", e);
        }
    }

    @Override
    public List<Individual> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualRepository individualRepository = sqlSession.getMapper(IndividualRepository.class);
            return individualRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find individual.", e);
        }
    }

    @Override
    public void update(Individual individual) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualRepository individualRepository = sqlSession.getMapper(IndividualRepository.class);
            individualRepository.update(individual);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update individual.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IndividualRepository individualRepository = sqlSession.getMapper(IndividualRepository.class);
            individualRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete individual.", e);
        }
    }
}
