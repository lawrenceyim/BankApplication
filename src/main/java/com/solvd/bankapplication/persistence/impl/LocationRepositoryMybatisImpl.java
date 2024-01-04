package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Location;
import com.solvd.bankapplication.persistence.LocationRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class LocationRepositoryMybatisImpl implements LocationRepository {
    @Override
    public void create(Location location) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LocationRepository locationRepository = sqlSession.getMapper(LocationRepository.class);
            locationRepository.create(location);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create location.", e);
        }
    }

    @Override
    public Optional<Location> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LocationRepository locationRepository = sqlSession.getMapper(LocationRepository.class);
            return locationRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find location.", e);
        }
    }

    @Override
    public List<Location> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LocationRepository locationRepository = sqlSession.getMapper(LocationRepository.class);
            return locationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find location.", e);
        }
    }

    @Override
    public void update(Location location) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LocationRepository locationRepository = sqlSession.getMapper(LocationRepository.class);
            locationRepository.update(location);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update location.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LocationRepository locationRepository = sqlSession.getMapper(LocationRepository.class);
            locationRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete location.", e);
        }
    }
}
