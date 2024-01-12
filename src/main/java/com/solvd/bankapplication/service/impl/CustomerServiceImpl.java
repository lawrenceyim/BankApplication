package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";

    private CustomerDao customerDao;

    public CustomerServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            customerDao = (CustomerDao) Class.forName(classPath + "CustomerDaoImpl").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            logger.info("No customers found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s\n", "Customer ID", "Email", "Phone Number"));
        customers.stream().forEach(customer -> {
            sb.append(String.format("%-30s%-30s%-30s\n", customer.getCustomerID(), customer.getEmail(), customer.getPhoneNumber()));
        });
        logger.info(sb.toString());
    }
}
