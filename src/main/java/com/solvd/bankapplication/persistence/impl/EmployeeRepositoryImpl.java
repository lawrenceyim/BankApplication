package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Employee;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Employees(bank_id, first_name, middle_Name, last_name, position, branch_id, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, employee.getBankID());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setLong(5, employee.getPosition());
            preparedStatement.setLong(6, employee.getBranchID());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setEmployeeID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Employees WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete employee.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Employee employee = null;
        final String query = "SELECT employee_id, bank_id, first_name, middle_Name, last_name, position, branch_id, " +
                "email FROM Employees WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeID(resultSet.getLong(1));
                employee.setBankID(resultSet.getLong(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setMiddleName(resultSet.getString(4));
                employee.setLastName(resultSet.getString(5));
                employee.setPosition(resultSet.getLong(6));
                employee.setBranchID(resultSet.getLong(7));
                employee.setEmail(resultSet.getString(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find employee.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public void update(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Employees SET bank_id = ?, first_name = ?, middle_Name = ?, last_name = ?, "
                + "position = ?, branch_id = ?, email = ? WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employee.getBankID());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setLong(5, employee.getPosition());
            preparedStatement.setLong(6, employee.getBranchID());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setLong(8, employee.getEmployeeID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update employee.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Employee> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Employee> employees = new ArrayList<>();
        final String query = "SELECT employee_id, bank_id, first_name, middle_Name, last_name, position, branch_id, " +
                "email FROM Employees";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getLong(1));
                employee.setBankID(resultSet.getLong(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setMiddleName(resultSet.getString(4));
                employee.setLastName(resultSet.getString(5));
                employee.setPosition(resultSet.getLong(6));
                employee.setBranchID(resultSet.getLong(7));
                employee.setEmail(resultSet.getString(8));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find employee.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employees;
    }
}
