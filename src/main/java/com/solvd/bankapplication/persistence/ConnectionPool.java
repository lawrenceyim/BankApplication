package com.solvd.bankapplication.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class ConnectionPool {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String url = "jdbc:mysql://localhost:3306/BankDB";
    private final String user = "root";
    private final String password = "password";
    private final int MAX_CONNECTIONS = 5;
    private static ConnectionPool instance = null;
    private volatile CopyOnWriteArrayList<Connection> occupiedConnections = new CopyOnWriteArrayList<>();
    private volatile ConcurrentLinkedQueue<Connection> freeConnections = new ConcurrentLinkedQueue<>();

    private ConnectionPool() {
        IntStream.rangeClosed(1, MAX_CONNECTIONS).forEach(i -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
            freeConnections.add(connection);
        });
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (freeConnections.isEmpty()) {
            return null;
        }
        Connection connection = freeConnections.remove();
        occupiedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        occupiedConnections.remove(connection);
        freeConnections.add(connection);
    }
}
