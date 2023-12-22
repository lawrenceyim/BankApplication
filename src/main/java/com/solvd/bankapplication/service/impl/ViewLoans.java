package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.dao.impl.QueryExecutor;
import com.solvd.bankapplication.service.IService;
import com.solvd.bankapplication.util.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ViewLoans implements IService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");

    @Override
    public void performService() {
        final File file = new File("src/main/resources/ViewLoansQuery.txt");
        try {
            final ArrayList<String> queries = Parser.parseSqlFromFile(file);
            final QueryExecutor queryExecutor = new QueryExecutor();
            queries.stream().forEach(query -> {
                queryExecutor.executeQuery(query);
            });
            final ResultSet resultSet = queryExecutor.retrieveResult();
            while (resultSet.next()) {
                final int loanId = resultSet.getInt("loan_id");
                final int customerId = resultSet.getInt("customer_id");
                final double loanAmount = resultSet.getDouble("loan_amount");
                final Timestamp date = resultSet.getTimestamp("date");

                System.out.println("Loan ID: " + loanId +
                        ", Customer ID: " + customerId +
                        ", Loan Amount: " + loanAmount +
                        ", Date: " + date);
            }
        } catch (SQLException e) {
            logger.error("Invalid SQL query during database initialization");
        }
    }
}
