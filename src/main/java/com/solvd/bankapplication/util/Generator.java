package com.solvd.bankapplication.util;

import com.solvd.bankapplication.persistence.impl.QueryExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.util.ArrayList;

public class Generator {
    private static final Logger logger = (Logger) LogManager.getLogger("Output");

    public static void createDatabase() {
        final File file = new File("src/main/resources/DatabaseGenerationQuery.txt");
        final ArrayList<String> queries = Parser.parseSqlFromFile(file);
        final QueryExecutor queryExecutor = new QueryExecutor();
        queries.stream().forEach(query -> {
            queryExecutor.executeUpdate(query);
        });
    }

    public static void fillDataBase() {
        final File file = new File("src/main/resources/FillDatabase.txt");
        final ArrayList<String> queries = Parser.parseSqlFromFile(file);
        final QueryExecutor queryExecutor = new QueryExecutor();
        queries.stream().forEach(query -> {
            queryExecutor.executeUpdate(query);
        });
    }
}
