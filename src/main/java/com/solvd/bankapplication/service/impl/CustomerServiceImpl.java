package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.persistence.impl.CustomerDaoImpl;
import com.solvd.bankapplication.persistence.impl.CustomerDaoMybatisImpl;
import com.solvd.bankapplication.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");

    private CustomerDao customerDao;

    public CustomerServiceImpl(String mode) {
        if (mode.equals("mybatis")) {
            customerDao = new CustomerDaoMybatisImpl();
        } else if (mode.equals("jdbc")) {
            customerDao = new CustomerDaoImpl();
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
