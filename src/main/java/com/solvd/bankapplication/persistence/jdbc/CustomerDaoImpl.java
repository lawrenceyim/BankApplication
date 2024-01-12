package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Customers(bank_id, email, phone_number, street_address, city, country) VALUES (?, ?, ?, ?, ?, ?)";
    private final String findByIdQuery = "SELECT customer_id, bank_id, email, phone_number, street_address, city, country FROM Customers WHERE customer_id = ?";
    private final String findAllQuery = "SELECT customer_id, bank_id, email, phone_number, street_address, city, country FROM Customers";
    private final String updateQuery = "UPDATE Customers SET bank_id = ?, email = ?, phone_number = ?, street_address = ?, city = ?, country = ? WHERE customer_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Customers WHERE customer_id = ?";

    @Override
    public void create(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, customer.getBankID());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getStreetAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getCountry());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    customer.setCustomerID(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create customer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Customer> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setCustomerID(resultSet.getLong(1));
                    customer.setBankID(resultSet.getLong(2));
                    customer.setEmail(resultSet.getString(3));
                    customer.setPhoneNumber(resultSet.getString(4));
                    customer.setStreetAddress(resultSet.getString(5));
                    customer.setCity(resultSet.getString(6));
                    customer.setCountry(resultSet.getString(7));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find customer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerID(resultSet.getLong(1));
                    customer.setBankID(resultSet.getLong(2));
                    customer.setEmail(resultSet.getString(3));
                    customer.setPhoneNumber(resultSet.getString(4));
                    customer.setStreetAddress(resultSet.getString(5));
                    customer.setCity(resultSet.getString(6));
                    customer.setCountry(resultSet.getString(7));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find customer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setLong(1, customer.getBankID());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getStreetAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getCountry());
            preparedStatement.setLong(7, customer.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update customer.", e);
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
            throw new RuntimeException("Unable to delete customer.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
