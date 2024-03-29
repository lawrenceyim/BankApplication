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
    private final String createQuery = "INSERT INTO Businesses(customer_id, business_name) VALUES (?, ?)";
    private final String findByIdQuery = "SELECT customer_id, business_name FROM Businesses WHERE customer_id = ?";
    private final String findAllQuery = "SELECT customer_id, business_name FROM Businesses";
    private final String updateQuery = "UPDATE Businesses SET business_name = ? WHERE customer_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Businesses WHERE customer_id = ?";

    @Override
    public void create(Business business) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    business = new Business();
                    business.setCustomerID(resultSet.getLong(1));
                    business.setBusinessName(resultSet.getString(2));
                }
            }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Business business = new Business();
                    business.setCustomerID(resultSet.getLong(1));
                    business.setBusinessName(resultSet.getString(2));
                    businesses.add(business);
                }
            }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete business.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
