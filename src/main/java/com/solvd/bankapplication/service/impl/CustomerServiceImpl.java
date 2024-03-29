package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerDao;
import com.solvd.bankapplication.service.CustomerService;
import com.solvd.bankapplication.utils.ClassInstantiation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private CustomerDao customerDao = ClassInstantiation.generateClassInstance("CustomerDaoImpl");

    @Override
    public void findAll() {
        List<Customer> customers = customerDao.findAll();
        if (customers.isEmpty()) {
            OUTPUT_LOGGER.info("No customers found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s" + System.lineSeparator(), "Customer ID", "Email", "Phone Number"));
        customers.stream().forEach(customer -> {
            sb.append(String.format("%-30s%-30s%-30s" + System.lineSeparator(), customer.getCustomerID(), customer.getEmail(), customer.getPhoneNumber()));
        });
        OUTPUT_LOGGER.info(sb.toString());
    }
}
