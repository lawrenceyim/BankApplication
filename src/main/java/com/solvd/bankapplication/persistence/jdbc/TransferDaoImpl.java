package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransferDaoImpl implements TransferDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Transfers(date, amount, from_account_id, to_account_id) VALUES (?, ?, ?, ?)";
    private final String findByIdQuery = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers WHERE transfer_id = ?";
    private final String findAllQuery = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers";
    private final String findAllByAccountQuery = "SELECT transfer_id, date, amount, from_account_id, to_account_id FROM Transfers WHERE from_account_id = ? OR to_account_id = ?";
    private final String updateQuery = "UPDATE Transfers SET date = ?, amount = ?, from_account_id = ?, to_account_id = ? WHERE transfer_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Transfers WHERE transfer_id = ?";

    @Override
    public void create(Transfer transfer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, transfer.getDate());
            preparedStatement.setBigDecimal(2, transfer.getAmount());
            preparedStatement.setLong(3, transfer.getFromAccountID());
            preparedStatement.setLong(4, transfer.getToAccountID());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    transfer.setTransferID(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Transfer> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Transfer transfer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transfer = new Transfer();
                    transfer.setTransferID(resultSet.getLong(1));
                    transfer.setDate(resultSet.getTimestamp(2));
                    transfer.setAmount(resultSet.getBigDecimal(3));
                    transfer.setFromAccountID(resultSet.getLong(4));
                    transfer.setToAccountID(resultSet.getLong(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(transfer);
    }

    @Override
    public List<Transfer> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Transfer> transfers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transfer transfer = new Transfer();
                    transfer.setTransferID(resultSet.getLong(1));
                    transfer.setDate(resultSet.getTimestamp(2));
                    transfer.setAmount(resultSet.getBigDecimal(3));
                    transfer.setFromAccountID(resultSet.getLong(4));
                    transfer.setToAccountID(resultSet.getLong(5));
                    transfers.add(transfer);
                }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllByAccountQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transfer transfer = new Transfer();
                    transfer.setTransferID(resultSet.getLong(1));
                    transfer.setDate(resultSet.getTimestamp(2));
                    transfer.setAmount(resultSet.getBigDecimal(3));
                    transfer.setFromAccountID(resultSet.getLong(4));
                    transfer.setToAccountID(resultSet.getLong(5));
                    transfers.add(transfer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return transfers;
    }

    @Override
    public void update(Transfer transfer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
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
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete transfer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
