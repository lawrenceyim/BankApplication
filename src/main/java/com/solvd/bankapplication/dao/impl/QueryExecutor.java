package com.solvd.bankapplication.dao.impl;

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

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void executeQuery(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Invalid SQL query.");
        }
    }

    @Override
    public ResultSet retrieveResult() {
        return resultSet;
    }
}
