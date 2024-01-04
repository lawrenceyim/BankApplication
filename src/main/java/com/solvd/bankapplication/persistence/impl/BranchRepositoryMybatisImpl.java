package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Branch;
import com.solvd.bankapplication.persistence.BranchRepository;
import com.solvd.bankapplication.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BranchRepositoryMybatisImpl implements BranchRepository {
    @Override
    public void create(Branch branch) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.create(branch);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create branch.", e);
        }
    }

    @Override
    public Optional<Branch> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            return branchRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find branch.", e);
        }
    }

    @Override
    public List<Branch> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            return branchRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find branch.", e);
        }
    }

    @Override
    public void update(Branch branch) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.update(branch);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update branch.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            BranchRepository branchRepository = sqlSession.getMapper(BranchRepository.class);
            branchRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete branch.", e);
        }
    }
}
