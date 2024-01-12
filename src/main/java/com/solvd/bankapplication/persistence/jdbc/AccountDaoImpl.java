package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Accounts(customer_id, account_type, balance) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, account.getCustomerID());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.setBigDecimal(3, account.getBalance());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setAccountID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Account account = null;
        final String query = "SELECT account_id, customer_id, account_type, balance FROM Accounts WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setAccountID(resultSet.getLong(1));
                account.setCustomerID(resultSet.getLong(2));
                account.setAccountType(resultSet.getString(3));
                account.setBalance(resultSet.getBigDecimal(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Account> accounts = new ArrayList<>();
        final String query = "SELECT account_id, customer_id, account_type, balance FROM Accounts";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountID(resultSet.getLong(1));
                account.setCustomerID(resultSet.getLong(2));
                account.setAccountType(resultSet.getString(3));
                account.setBalance(resultSet.getBigDecimal(4));
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return accounts;
    }

    @Override
    public List<Account> findByCustomer(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Account> accounts = new ArrayList<>();
        final String query = "SELECT account_id, customer_id, account_type, balance FROM Accounts WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountID(resultSet.getLong(1));
                account.setCustomerID(resultSet.getLong(2));
                account.setAccountType(resultSet.getString(3));
                account.setBalance(resultSet.getBigDecimal(4));
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return accounts;
    }


    @Override
    public void update(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Accounts SET customer_id = ?, account_type = ?, balance = ? WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, account.getCustomerID());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.setBigDecimal(3, account.getBalance());
            preparedStatement.setLong(4, account.getAccountID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Accounts WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete account.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
