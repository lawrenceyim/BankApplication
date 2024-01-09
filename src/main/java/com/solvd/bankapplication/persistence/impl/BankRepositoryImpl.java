package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Bank;
import com.solvd.bankapplication.persistence.BankRepository;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankRepositoryImpl implements BankRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Bank bank) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Banks(bank_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                bank.setBankID(resultSet.getLong(1));
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
        final String query = "SELECT bank_id, bank_name FROM Banks WHERE bank_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bank = new Bank();
                bank.setBankID(resultSet.getLong(1));
                bank.setBankName(resultSet.getString(2));
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
        final String query = "SELECT bank_id, bank_name FROM Banks";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bank bank = new Bank();
                bank.setBankID(resultSet.getLong(1));
                bank.setBankName(resultSet.getString(2));
                banks.add(bank);
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
        final String query = "UPDATE Banks bank_name = ? WHERE bank_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        final String query = "DELETE FROM Banks WHERE bank_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete bank.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
