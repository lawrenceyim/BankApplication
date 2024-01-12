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
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.create(card);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Card> findById(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findById(id);
        }
    }

    @Override
    public List<Card> findAll() {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findAll();
        }
    }

    @Override
    public List<Card> findAllByAccount(long id) {
        try (SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false)) {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            return cardDao.findAllByAccount(id);
        }
    }

    @Override
    public void update(Card card) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.update(card);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteById(long id) {
        SqlSession sqlSession = MyBatisSessionFactory.getSessionFactory().openSession(false);
        try {
            CardDao cardDao = sqlSession.getMapper(CardDao.class);
            cardDao.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
