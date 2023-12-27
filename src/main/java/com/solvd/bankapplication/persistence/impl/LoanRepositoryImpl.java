package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Loan;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.LoanRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepositoryImpl implements LoanRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Loan loan) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Loans(customer_id, loan_amount, date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, loan.getLoanID());
            preparedStatement.setBigDecimal(2, loan.getLoanAmount());
            preparedStatement.setTimestamp(3, loan.getDate());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                loan.setLoanID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create loan.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Loans WHERE loan_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete loan.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Loan> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Loan loan = null;
        final String query = "SELECT loan_id, customer_id, loan_amount, date FROM Loans WHERE loan_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loan = new Loan();
                loan.setLoanID(resultSet.getLong(1));
                loan.setCustomerID(resultSet.getLong(2));
                loan.setLoanAmount(resultSet.getBigDecimal(3));
                loan.setDate(resultSet.getTimestamp(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find loan.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(loan);
    }

    @Override
    public void update(Loan loan) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Loans SET customer_id = ?, loan_amount = ?, date = ? WHERE loan_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, loan.getCustomerID());
            preparedStatement.setBigDecimal(2, loan.getLoanAmount());
            preparedStatement.setTimestamp(3, loan.getDate());
            preparedStatement.setLong(4, loan.getLoanID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update loan.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Loan> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Loan> loans = new ArrayList<>();
        final String query = "SELECT loan_id, customer_id, amount, card_id FROM Loans";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Loan loan = new Loan();
                loan.setLoanID(resultSet.getLong(1));
                loan.setCustomerID(resultSet.getLong(2));
                loan.setLoanAmount(resultSet.getBigDecimal(3));
                loan.setDate(resultSet.getTimestamp(4));
                loans.add(loan);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find loan.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return loans;
    }
}
