package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankDaoImpl implements BankDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Banks(bank_name) VALUES (?)";
    private final String findByQuery = "SELECT bank_id, bank_name FROM Banks WHERE bank_id = ?";
    private final String findAllQuery = "SELECT bank_id, bank_name FROM Banks";
    private final String updateQuery = "UPDATE Banks bank_name = ? WHERE bank_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Banks WHERE bank_id = ?";

    @Override
    public void create(Bank bank) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    bank.setBankID(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create bank.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Bank> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Bank bank = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bank = new Bank();
                    bank.setBankID(resultSet.getLong(1));
                    bank.setBankName(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find bank.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(bank);
    }

    @Override
    public List<Bank> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Bank> banks = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Bank bank = new Bank();
                    bank.setBankID(resultSet.getLong(1));
                    bank.setBankName(resultSet.getString(2));
                    banks.add(bank);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find bank.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return banks;
    }

    @Override
    public void update(Bank bank) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update bank.", e);
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
            throw new RuntimeException("Unable to delete bank.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
