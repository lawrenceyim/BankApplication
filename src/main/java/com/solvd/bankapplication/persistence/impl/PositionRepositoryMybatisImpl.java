package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Position;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import com.solvd.bankapplication.persistence.PositionRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PositionRepositoryMybatisImpl implements PositionRepository {
    @Override
    public void create(Position position) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.create(position);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create position.", e);
        }
    }

    @Override
    public Optional<Position> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            return positionRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find position.", e);
        }
    }

    @Override
    public List<Position> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            return positionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find position.", e);
        }
    }

    @Override
    public void update(Position position) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.update(position);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update position.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete position.", e);
        }
    }
}
