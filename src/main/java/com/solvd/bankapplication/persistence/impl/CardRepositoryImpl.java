package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.CardRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Card card) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Cards(card_type, account_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, card.getCardType());
            preparedStatement.setLong(2, card.getAccountID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                card.setCardID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Cards WHERE card_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Card> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Card card = null;
        final String query = "SELECT card_id, card_type, account_id FROM FROM Cards WHERE card_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                card = new Card();
                card.setCardID((resultSet.getLong(1)));
                card.setCardType(resultSet.getString(2));
                card.setAccountID(resultSet.getLong(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(card);
    }

    @Override
    public void update(Card card) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Payments SET card_type = ?, account_id = ? WHERE card_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, card.getCardType());
            preparedStatement.setLong(2, card.getAccountID());
            preparedStatement.setLong(3, card.getCardID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Card> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Card> cards = new ArrayList<>();
        final String query = "SELECT card_id, card_type, account_id FROM Cards";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardID(resultSet.getLong(1));
                card.setCardType(resultSet.getString(2));
                card.setAccountID(resultSet.getLong(3));
                cards.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cards;
    }

    @Override
    public List<Card> findAllByAccount(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Card> cards = new ArrayList<>();
        final String query = "SELECT card_id, card_type, account_id FROM Cards WHERE card_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardID(resultSet.getLong(1));
                card.setCardType(resultSet.getString(2));
                card.setAccountID(resultSet.getLong(3));
                cards.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cards;
    }
}
