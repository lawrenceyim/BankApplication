package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.persistence.CardRepository;
import com.solvd.bankapplication.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CardRepositoryMybatisImpl implements CardRepository {

    @Override
    public void create(Card card) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.create(card);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create card.", e);
        }
    }

    @Override
    public Optional<Card> findById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            return cardRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public List<Card> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            return cardRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public List<Card> findAllByAccount(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            return cardRepository.findAllByAccount(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public void update(Card card) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.update(card);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update card.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            CardRepository cardRepository = sqlSession.getMapper(CardRepository.class);
            cardRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete card.", e);
        }
    }
}
