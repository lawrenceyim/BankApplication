package com.solvd.bankapplication.dao.impl;

import com.solvd.bankapplication.connection.ConnectionPool;
import com.solvd.bankapplication.dao.IMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.*;

public class QueryExecutor implements IMapper {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private Connection connection;
    private ResultSet resultSet;

    @Override
    public void executeQuery(String query) {
        try {
            while (connection == null) {
                connection = ConnectionPool.getInstance().getConnection();
            }
            PreparedStatement statement = connection.prepareStatement("USE BankDB");
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
        } catch (SQLException e) {
            logger.error(e.toString());
            System.exit(1);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public void executeUpdate(String query) {
        try {
            while (connection == null) {
                connection = ConnectionPool.getInstance().getConnection();
            }
            PreparedStatement statement = connection.prepareStatement("USE BankDB");
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e.toString());
            System.exit(1);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public ResultSet retrieveResult() {
        return resultSet;
    }
}
