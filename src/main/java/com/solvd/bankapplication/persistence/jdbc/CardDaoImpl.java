package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDaoImpl implements CardDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Cards(card_type, account_id) VALUES (?, ?)";
    private final String findByIdQuery = "SELECT card_id, card_type, account_id FROM FROM Cards WHERE card_id = ?";
    private final String findAllQuery = "SELECT card_id, card_type, account_id FROM Cards";
    private final String findAllByAccountQuery = "SELECT card_id, card_type, account_id FROM Cards WHERE card_id = ?";
    private final String updateQuery = "UPDATE Payments SET card_type = ?, account_id = ? WHERE card_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Cards WHERE card_id = ?";

    @Override
    public void create(Card card) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, card.getCardType());
            preparedStatement.setLong(2, card.getAccountID());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    card.setCardID(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Card> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Card card = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    card = new Card();
                    card.setCardID((resultSet.getLong(1)));
                    card.setCardType(resultSet.getString(2));
                    card.setAccountID(resultSet.getLong(3));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(card);
    }

    @Override
    public List<Card> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Card> cards = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Card card = new Card();
                    card.setCardID(resultSet.getLong(1));
                    card.setCardType(resultSet.getString(2));
                    card.setAccountID(resultSet.getLong(3));
                    cards.add(card);
                }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllByAccountQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Card card = new Card();
                    card.setCardID(resultSet.getLong(1));
                    card.setCardType(resultSet.getString(2));
                    card.setAccountID(resultSet.getLong(3));
                    cards.add(card);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return cards;
    }

    @Override
    public void update(Card card) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
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
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete card.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
