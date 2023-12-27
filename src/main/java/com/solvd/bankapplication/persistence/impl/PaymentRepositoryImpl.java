package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.bin.Payment;
import com.solvd.bankapplication.connection.ConnectionPool;
import com.solvd.bankapplication.persistence.PaymentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void create(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Payments(company, date, amount, card_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, payment.getCompanyName());
            preparedStatement.setTimestamp(2, payment.getDate());
            preparedStatement.setBigDecimal(3, payment.getAmount());
            preparedStatement.setLong(4, payment.getCardID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                payment.setPaymentID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Payments WHERE payment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Payment> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Payment payment = null;
        final String query = "SELECT * FROM Payments WHERE payment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                payment = new Payment();
                payment.setPaymentID(resultSet.getLong(1));
                payment.setCompanyName(resultSet.getString(2));
                payment.setDate(resultSet.getTimestamp(3));
                payment.setAmount(resultSet.getBigDecimal(4));
                payment.setCardID(resultSet.getLong(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public void update(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Payments SET company = ?, date = ?, amount = ?, card_id = ? WHERE payment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
    public List<Payment> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Payment> payments = new ArrayList<>();
        final String query = "SELECT * FROM Payments";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(resultSet.getLong(1));
                payment.setCompanyName(resultSet.getString(2));
                payment.setDate(resultSet.getTimestamp(3));
                payment.setAmount(resultSet.getBigDecimal(4));
                payment.setCardID(resultSet.getLong(5));
                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find payment.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return payments;
    }
}
