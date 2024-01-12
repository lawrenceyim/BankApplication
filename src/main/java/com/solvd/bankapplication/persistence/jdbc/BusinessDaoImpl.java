package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Business;
import com.solvd.bankapplication.persistence.BusinessDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusinessDaoImpl implements BusinessDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Business business) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Businesses(customer_id, business_name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, business.getCustomerID());
            preparedStatement.setString(2, business.getBusinessName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Business> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Business business = null;
        final String query = "SELECT customer_id, business_name FROM Businesses WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                business = new Business();
                business.setCustomerID(resultSet.getLong(1));
                business.setBusinessName(resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(business);
    }

    @Override
    public List<Business> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Business> businesses = new ArrayList<>();
        final String query = "SELECT customer_id, business_name FROM Businesses";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Business business = new Business();
                business.setCustomerID(resultSet.getLong(1));
                business.setBusinessName(resultSet.getString(2));
                businesses.add(business);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return businesses;
    }

    @Override
    public void update(Business business) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Businesses SET business_name = ? WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, business.getBusinessName());
            preparedStatement.setLong(2, business.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Businesses WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
