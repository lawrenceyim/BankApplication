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

    @Override
    public void create(CustomerLoginDetail customerLoginDetail) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO CustomerLoginDetails(customer_id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        final String query = "SELECT customer_id, username, password FROM CustomerLoginDetails WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customerLoginDetail = new CustomerLoginDetail();
                customerLoginDetail.setCustomerID(resultSet.getLong(1));
                customerLoginDetail.setUsername(resultSet.getString(2));
                customerLoginDetail.setPassword(resultSet.getString(3));
            }
            resultSet.close();
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
        final String query = "SELECT customer_id, username, password FROM Payments";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerLoginDetail customerLoginDetail = new CustomerLoginDetail();
                customerLoginDetail.setCustomerID(resultSet.getLong(1));
                customerLoginDetail.setUsername(resultSet.getString(2));
                customerLoginDetail.setPassword(resultSet.getString(3));
                customerLoginDetails.add(customerLoginDetail);
            }
            resultSet.close();
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
        final String query = "UPDATE Payments SET username = ?, password = ? WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        final String query = "DELETE FROM CustomerLoginDetails WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete customer login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
