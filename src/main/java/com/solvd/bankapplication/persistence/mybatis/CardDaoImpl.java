package com.solvd.bankapplication.persistence.mybatis;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.utils.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CardDaoImpl implements CardDao {

    @Override
    public void create(Card card) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.create(card);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create card.", e);
        }
    }

    @Override
    public Optional<Card> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public List<Card> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public List<Card> findAllByAccount(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findAllByAccount(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find card.", e);
        }
    }

    @Override
    public void update(Card card) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.update(card);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update card.", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(true)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete card.", e);
        }
    }
}
