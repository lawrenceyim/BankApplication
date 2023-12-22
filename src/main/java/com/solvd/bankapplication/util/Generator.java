package com.solvd.bankapplication.util;

import com.solvd.bankapplication.connection.ConnectionPool;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Generator {
    private static final Logger logger = (Logger) LogManager.getLogger("Output");

    public static void createDatabase() {
        try {
            final File file = new File("src/main/resources/DatabaseGenerationQuery.txt");
            final String query = FileUtils.readFileToString(file, "UTF-8").trim();
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate(query);
            preparedStatement.execute();
        } catch (IOException e) {
            logger.error("Invalid file");
        } catch (SQLException e) {
            logger.error("Invalid SQL query during database initialization");
        }
    }

    public static void fillDataBase() {
        try {
            final File file = new File("src/main/resources/FillDatabase.txt");
            final String query = FileUtils.readFileToString(file, "UTF-8").trim();
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate(query);
            preparedStatement.execute();
        } catch (IOException e) {
            logger.error("Invalid file");
        } catch (SQLException e) {
            logger.error("Invalid SQL query during database initialization");
        }
    }
}
