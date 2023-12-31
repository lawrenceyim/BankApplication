package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Position;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.PositionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionRepositoryImpl implements PositionRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Positions(title) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, position.getTitle());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                position.setPositionID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create position.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Positions WHERE position_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete position.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Position> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Position position = null;
        final String query = "SELECT position_id, title FROM Positions WHERE position_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                position = new Position();
                position.setPositionID(resultSet.getLong(1));
                position.setTitle(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find position.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(position);
    }

    @Override
    public void update(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Positions SET title = ? WHERE position_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position.getTitle());
            preparedStatement.setLong(2, position.getPositionID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update position.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Position> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Position> positions = new ArrayList<>();
        final String query = "SELECT position_id, title FROM Positions";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Position position = new Position();
                position.setPositionID(resultSet.getLong(1));
                position.setTitle(resultSet.getString(2));
                positions.add(position);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find position.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return positions;
    }
}
