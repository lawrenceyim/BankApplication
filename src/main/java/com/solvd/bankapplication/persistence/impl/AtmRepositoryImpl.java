package com.solvd.bankapplication.persistence.impl;

import com.solvd.bankapplication.domain.Atm;
import com.solvd.bankapplication.persistence.AtmRepository;
import com.solvd.bankapplication.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AtmRepositoryImpl implements AtmRepository {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Atm atm) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "INSERT INTO Atms(location_id, cash_amount) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, atm.getLocationID());
            preparedStatement.setBigDecimal(2, atm.getCashAmount());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                atm.setAtmID(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create atm.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "DELETE FROM Atms WHERE atm_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete atm.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Atm> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Atm atm = null;
        final String query = "SELECT atm_id, location_id, cash_amount FROM Atms WHERE atm_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                atm = new Atm();
                atm.setAtmID(resultSet.getLong(1));
                atm.setLocationID(resultSet.getLong(2));
                atm.setCashAmount(resultSet.getBigDecimal(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find atm.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(atm);
    }

    @Override
    public void update(Atm atm) {
        Connection connection = CONNECTION_POOL.getConnection();
        final String query = "UPDATE Atms SET location_id = ?, cash_amount = ? WHERE atm_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, atm.getLocationID());
            preparedStatement.setBigDecimal(2, atm.getCashAmount());
            preparedStatement.setLong(3, atm.getAtmID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update atm.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Atm> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Atm> atms = new ArrayList<>();
        final String query = "SELECT atm_id, location_id, cash_amount FROM Atms";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Atm atm = new Atm();
                atm.setAtmID(resultSet.getLong(1));
                atm.setLocationID(resultSet.getLong(2));
                atm.setCashAmount(resultSet.getBigDecimal(3));
                atms.add(atm);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find atm.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return atms;
    }
}
