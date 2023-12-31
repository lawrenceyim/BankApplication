package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Atm;
import com.solvd.bankapplication.persistence.AtmRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AtmRepositoryMyBatisImpl implements AtmRepository {
    @Override
    public void create(Atm atm) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.create(atm);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create atm.", e);
        }
    }

    @Override
    public Optional<Atm> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            return atmRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find atm.", e);
        }
    }

    @Override
    public List<Atm> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            return atmRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find atm.", e);
        }
    }

    @Override
    public void update(Atm atm) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.update(atm);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update atm.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete atm.", e);
        }
    }
}
