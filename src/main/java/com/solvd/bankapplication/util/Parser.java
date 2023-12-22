package com.solvd.bankapplication.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static final Logger logger = (Logger) LogManager.getLogger("Output");

    public static ArrayList<String> parseSqlFromFile(File file) {
        ArrayList<String> queries = new ArrayList();
        try {
            final String content = FileUtils.readFileToString(file, "UTF-8").trim();
            String[] parts = content.split(";");
            Arrays.stream(parts).forEach(part -> {
                String query = part + ";";
                queries.add(query);
            });
        } catch (IOException e) {
            logger.error("Invalid file.");
        }
        return queries;
    }
}
