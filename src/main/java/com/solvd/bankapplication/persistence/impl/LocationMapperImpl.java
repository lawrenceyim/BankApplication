package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Location;
import com.solvd.bankapplication.persistence.ConnectionPool;
import com.solvd.bankapplication.persistence.LocationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationMapperImpl implements LocationRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Location location) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Locations(street_address, city, country) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, location.getStreetAddress());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                location.setLocationID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create location.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Locations WHERE location_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete location.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Location> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Location location = null;
        final String query = "SELECT location_id, street_address, city, country FROM Locations WHERE location_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                location = new Location();
                location.setLocationID(resultSet.getLong(1));
                location.setStreetAddress(resultSet.getString(2));
                location.setCity(resultSet.getString(3));
                location.setCountry(resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find location.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(location);
    }

    @Override
    public void update(Location location) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Locations SET street_address = ?, city = ?, country = ? WHERE location_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, location.getStreetAddress());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getCountry());
            preparedStatement.setLong(4, location.getLocationID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update location.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Location> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Location> locations = new ArrayList<>();
        final String query = "SELECT location_id, street_address, city, country FROM Locations";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Location location = new Location();
                location.setLocationID(resultSet.getLong(1));
                location.setStreetAddress(resultSet.getString(2));
                location.setCity(resultSet.getString(3));
                location.setCountry(resultSet.getString(4));
                locations.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find location.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return locations;
    }
}
