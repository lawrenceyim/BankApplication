package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.utils.ConnectionPool;
import com.solvd.bankapplication.persistence.TransferRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransferRepositoryImpl implements TransferRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Transfer transfer) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Transfers(date, amount, from_account_id, to_account_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, transfer.getDate());
            preparedStatement.setBigDecimal(2, transfer.getAmount());
            preparedStatement.setLong(3, transfer.getFromAccountID());
            preparedStatement.setLong(4, transfer.getToAccountID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                transfer.setTransferID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Transfers WHERE transfer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Transfer> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Transfer transfer = null;
        final String query = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers WHERE transfer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                transfer = new Transfer();
                transfer.setTransferID(resultSet.getLong(1));
                transfer.setDate(resultSet.getTimestamp(2));
                transfer.setAmount(resultSet.getBigDecimal(3));
                transfer.setFromAccountID(resultSet.getLong(4));
                transfer.setToAccountID(resultSet.getLong(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(transfer);
    }

    @Override
    public void update(Transfer transfer) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Transfers SET date = ?, amount = ?, from_account_id = ?, to_account_id = ? WHERE transfer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, transfer.getDate());
            preparedStatement.setBigDecimal(2, transfer.getAmount());
            preparedStatement.setLong(3, transfer.getToAccountID());
            preparedStatement.setLong(4, transfer.getFromAccountID());
            preparedStatement.setLong(5, transfer.getTransferID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Transfer> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Transfer> transfers = new ArrayList<>();
        final String query = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transfer transfer = new Transfer();
                transfer.setTransferID(resultSet.getLong(1));
                transfer.setDate(resultSet.getTimestamp(2));
                transfer.setAmount(resultSet.getBigDecimal(3));
                transfer.setFromAccountID(resultSet.getLong(4));
                transfer.setToAccountID(resultSet.getLong(5));
                transfers.add(transfer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return transfers;
    }

    @Override
    public List<Transfer> findAllByAccount(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Transfer> transfers = new ArrayList<>();
        final String query = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers WHERE from_account_id = ? OR to_account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transfer transfer = new Transfer();
                transfer.setTransferID(resultSet.getLong(1));
                transfer.setDate(resultSet.getTimestamp(2));
                transfer.setAmount(resultSet.getBigDecimal(3));
                transfer.setFromAccountID(resultSet.getLong(4));
                transfer.setToAccountID(resultSet.getLong(5));
                transfers.add(transfer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return transfers;
    }
}
