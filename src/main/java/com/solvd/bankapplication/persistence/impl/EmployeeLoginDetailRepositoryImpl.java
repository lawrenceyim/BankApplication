package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.EmployeeLoginDetail;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.EmployeeLoginDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeLoginDetailRepositoryImpl implements EmployeeLoginDetailRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(EmployeeLoginDetail employeeLoginDetail) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO EmployeeLoginDetails(employee_id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeLoginDetail.getEmployeeID());
            preparedStatement.setString(2, employeeLoginDetail.getUsername());
            preparedStatement.setString(3, employeeLoginDetail.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM EmployeeLoginDetails WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete employee login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<EmployeeLoginDetail> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        EmployeeLoginDetail employeeLoginDetail = null;
        final String query = "SELECT employee_id, username, password FROM EmployeeLoginDetails WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employeeLoginDetail = new EmployeeLoginDetail();
                employeeLoginDetail.setEmployeeID(resultSet.getLong(1));
                employeeLoginDetail.setUsername(resultSet.getString(2));
                employeeLoginDetail.setPassword(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find employee login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(employeeLoginDetail);
    }

    @Override
    public void update(EmployeeLoginDetail employeeLoginDetail) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE EmployeeLoginDetails SET username = ?, password = ? WHERE employeeLoginDetail_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeLoginDetail.getUsername());
            preparedStatement.setString(2, employeeLoginDetail.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update employee login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<EmployeeLoginDetail> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<EmployeeLoginDetail> employeeLoginDetails = new ArrayList<>();
        final String query = "SELECT employee_id, username, password FROM EmployeeLoginDetails";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeeLoginDetail employeeLoginDetail = new EmployeeLoginDetail();
                employeeLoginDetail.setEmployeeID(resultSet.getLong(1));
                employeeLoginDetail.setUsername(resultSet.getString(2));
                employeeLoginDetail.setPassword(resultSet.getString(3));
                employeeLoginDetails.add(employeeLoginDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find employee login detail.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employeeLoginDetails;
    }
}
