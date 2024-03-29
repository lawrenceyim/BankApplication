package com.solvd.bankapplication.persistence.jdbc;

import com.solvd.bankapplication.domain.Individual;
import com.solvd.bankapplication.persistence.IndividualDao;
import com.solvd.bankapplication.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IndividualDaoImpl implements IndividualDao {
    private final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final String createQuery = "INSERT INTO Individuals(customer_id, first_name, middle_name, last_name) VALUES (?, ?, ?, ?)";
    private final String findByIqQuery = "SELECT customer_id, first_name, middle_name, last_name FROM Individuals WHERE customer_id = ?";
    private final String findAllQuery = "SELECT customer_id, first_name, middle_name, last_name FROM Individuals";
    private final String updateQuery = "UPDATE Individuals SET first_name = ?, middle_name = ?, last_name = ? WHERE customer_id = ?";
    private final String deleteByIdQuery = "DELETE FROM Individuals WHERE customer_id = ?";

    @Override
    public void create(Individual individual) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setLong(1, individual.getCustomerID());
            preparedStatement.setString(2, individual.getFirstName());
            preparedStatement.setString(3, individual.getMiddleName());
            preparedStatement.setString(4, individual.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create individual.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Individual> findById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Individual individual = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIqQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    individual = new Individual();
                    individual.setCustomerID(resultSet.getLong(1));
                    individual.setFirstName(resultSet.getString(2));
                    individual.setMiddleName(resultSet.getString(3));
                    individual.setLastName(resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find individual.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(individual);
    }

    @Override
    public List<Individual> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Individual> individuals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Individual individual = new Individual();
                    individual.setCustomerID(resultSet.getLong(1));
                    individual.setFirstName(resultSet.getString(2));
                    individual.setMiddleName(resultSet.getString(3));
                    individual.setLastName(resultSet.getString(4));
                    individuals.add(individual);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find individual.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return individuals;
    }

    @Override
    public void update(Individual individual) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, individual.getFirstName());
            preparedStatement.setString(2, individual.getMiddleName());
            preparedStatement.setString(3, individual.getLastName());
            preparedStatement.setLong(4, individual.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update individual.", e);
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
            throw new RuntimeException("Unable to delete individual.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
