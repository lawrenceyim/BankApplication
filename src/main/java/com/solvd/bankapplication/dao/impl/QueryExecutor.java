package com.solvd.bankapplication.dao.impl;

import com.solvd.bankapplication.connection.ConnectionPool;
import com.solvd.bankapplication.dao.IMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Invalid SQL query.");
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
