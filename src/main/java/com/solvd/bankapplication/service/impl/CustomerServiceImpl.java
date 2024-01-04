package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Customer;
import com.solvd.bankapplication.persistence.CustomerRepository;
import com.solvd.bankapplication.persistence.impl.CustomerRepositoryImpl;
import com.solvd.bankapplication.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public void findAll() {
        List<Customer> customers = customerRepository.findAll();
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
