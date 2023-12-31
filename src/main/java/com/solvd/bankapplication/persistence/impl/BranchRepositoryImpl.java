package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Branch;
import com.solvd.bankapplication.persistence.BranchRepository;
import com.solvd.bankapplication.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchRepositoryImpl implements BranchRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Branch branch) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Branches(location_id) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, branch.getLocationID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                branch.setBranchID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create branch.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Branch> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Branch branch = null;
        final String query = "SELECT branch_id, location_id FROM Branches WHERE branch_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                branch = new Branch();
                branch.setBranchID(resultSet.getLong(1));
                branch.setLocationID(resultSet.getLong(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find branch.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(branch);
    }

    @Override
    public List<Branch> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Branch> branches = new ArrayList<>();
        final String query = "SELECT branch_id,location_id FROM Branches";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setBranchID(resultSet.getLong(1));
                branch.setLocationID(resultSet.getLong(2));
                branches.add(branch);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find branch.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return branches;
    }

    @Override
    public void update(Branch branch) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Branches SET location_id = ? WHERE branch_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, branch.getLocationID());
            preparedStatement.setLong(2, branch.getBranchID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update branch.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Branches WHERE branch_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete branch.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
