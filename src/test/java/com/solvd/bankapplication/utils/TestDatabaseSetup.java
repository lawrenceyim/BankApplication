package com.solvd.bankapplication.utils;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseSetup {
    private static final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private static volatile boolean testDatabaseSetup = false;

    public static void instantiateTestDatabase() {
        if (!testDatabaseSetup) {
            setupTestDatabase();
            testDatabaseSetup = true;
        }
    }

    private static void setupTestDatabase() {
        final String databaseGenerationQueryFilepath = "src/test/resources/sql/DatabaseGenerationQuery.sql";
        final String databasePopulationQueryFilepath = "src/test/resources/sql/FillDatabase.sql";
        final String url = "jdbc:mysql://localhost:3306/BankDB";
        final String username = "root";
        final String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            FileReader generationQueryReader = new FileReader(databaseGenerationQueryFilepath);
            FileReader populationQueryReader = new FileReader(databasePopulationQueryFilepath);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(generationQueryReader);
            scriptRunner.runScript(populationQueryReader);
            generationQueryReader.close();
            populationQueryReader.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
