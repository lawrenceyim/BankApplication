package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.CustomerLoginDetail;
import com.solvd.bankapplication.persistence.CustomerLoginDetailDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerLoginDetailDaoImpl implements CustomerLoginDetailDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO CustomerLoginDetails(customer_id, username, password) VALUES (?, ?, ?)";
    private final String findByIdQuery = "SELECT customer_id, username, password FROM CustomerLoginDetails WHERE customer_id = ?";
    private final String findAllQuery = "SELECT customer_id, username, password FROM Payments";
    private final String updateQuery = "UPDATE Payments SET username = ?, password = ? WHERE customer_id = ?";
    private final String deleteByIdQuery = "DELETE FROM CustomerLoginDetails WHERE customer_id = ?";

    @Override
    public void create(CustomerLoginDetail customerLoginDetail) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setLong(1, customerLoginDetail.getCustomerID());
            preparedStatement.setString(2, customerLoginDetail.getUsername());
            preparedStatement.setString(3, customerLoginDetail.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create customer login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<CustomerLoginDetail> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        CustomerLoginDetail customerLoginDetail = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customerLoginDetail = new CustomerLoginDetail();
                    customerLoginDetail.setCustomerID(resultSet.getLong(1));
                    customerLoginDetail.setUsername(resultSet.getString(2));
                    customerLoginDetail.setPassword(resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(customerLoginDetail);
    }

    @Override
    public List<CustomerLoginDetail> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<CustomerLoginDetail> customerLoginDetails = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CustomerLoginDetail customerLoginDetail = new CustomerLoginDetail();
                    customerLoginDetail.setCustomerID(resultSet.getLong(1));
                    customerLoginDetail.setUsername(resultSet.getString(2));
                    customerLoginDetail.setPassword(resultSet.getString(3));
                    customerLoginDetails.add(customerLoginDetail);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find customer login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return customerLoginDetails;
    }

    @Override
    public void update(CustomerLoginDetail customerLoginDetail) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, customerLoginDetail.getUsername());
            preparedStatement.setString(2, customerLoginDetail.getPassword());
            preparedStatement.setLong(3, customerLoginDetail.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update customer login detail.", e);
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
            throw new RuntimeException("Unable to delete customer login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
