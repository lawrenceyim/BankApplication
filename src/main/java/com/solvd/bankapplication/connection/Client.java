package com.solvd.bankapplication.connection;

import com.solvd.bankapplication.service.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;

public class Client {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private Connection connection;
    private String name;
    private IService service;

    public Client(String name) {
        this.name = name;
    }

    public void getConnectionFromPool() {
        if (connection != null) {
            logger.trace("Client " + name + " already has a connection.");
            return;
        }
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        while (connection == null) {
            connection = connectionPool.getConnection();
        }
    }

    public void freeConnection() {
        ConnectionPool.getInstance().releaseConnection(connection);
        connection = null;
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }

    public void runService() {
        if (service != null) {
            service.performService();
        }
    }

    @Override
    public String toString() {
        return "Client " + name + " is connected to " + connection.toString();
    }
}
