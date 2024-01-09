package com.solvd.bankapplication.utils;

import com.solvd.bankapplication.menu.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class ConnectionPool {
    private final String configFile = "config.properties";
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final int MAX_CONNECTIONS = 5;
    private String url;
    private String user;
    private String password;
    private static ConnectionPool instance = null;
    private volatile CopyOnWriteArrayList<Connection> occupiedConnections = new CopyOnWriteArrayList<>();
    private volatile ConcurrentLinkedQueue<Connection> freeConnections = new ConcurrentLinkedQueue<>();

    private ConnectionPool() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
