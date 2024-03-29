package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Payments(company, date, amount, card_id) VALUES (?, ?, ?, ?)";
    private final String findByIqQuery = "SELECT payment_id, company, date, amount, card_id FROM Payments WHERE payment_id = ?";
    private final String findAllQuery = "SELECT payment_id, company, date, amount, card_id FROM Payments";
    private final String findAllByCardQuery = "SELECT payment_id, company, date, amount, card_id FROM Payments WHERE card_id = ?";
    private final String updateQuery = "UPDATE Payments SET company = ?, date = ?, amount = ?, card_id = ? WHERE payment_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Payments WHERE payment_id = ?";

    @Override
    public void create(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, payment.getCompanyName());
            preparedStatement.setTimestamp(2, payment.getDate());
            preparedStatement.setBigDecimal(3, payment.getAmount());
            preparedStatement.setLong(4, payment.getCardID());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    payment.setPaymentID(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Payment> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Payment payment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIqQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    payment = new Payment();
                    payment.setPaymentID(resultSet.getLong(1));
                    payment.setCompanyName(resultSet.getString(2));
                    payment.setDate(resultSet.getTimestamp(3));
                    payment.setAmount(resultSet.getBigDecimal(4));
                    payment.setCardID(resultSet.getLong(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public List<Payment> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentID(resultSet.getLong(1));
                    payment.setCompanyName(resultSet.getString(2));
                    payment.setDate(resultSet.getTimestamp(3));
                    payment.setAmount(resultSet.getBigDecimal(4));
                    payment.setCardID(resultSet.getLong(5));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return payments;
    }

    @Override
    public List<Payment> findAllByCard(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllByCardQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setPaymentID(resultSet.getLong(1));
                    payment.setCompanyName(resultSet.getString(2));
                    payment.setDate(resultSet.getTimestamp(3));
                    payment.setAmount(resultSet.getBigDecimal(4));
                    payment.setCardID(resultSet.getLong(5));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return payments;
    }

    @Override
    public void update(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, payment.getCompanyName());
            preparedStatement.setTimestamp(2, payment.getDate());
            preparedStatement.setBigDecimal(3, payment.getAmount());
            preparedStatement.setLong(4, payment.getCardID());
            preparedStatement.setLong(5, payment.getPaymentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update payment.", e);
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
            throw new RuntimeException("Unable to delete payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
