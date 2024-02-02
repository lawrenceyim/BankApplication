package com.solvd.bankapplication;

import com.solvd.bankapplication.service.CustomerService;
import com.solvd.bankapplication.service.impl.CustomerServiceImpl;
import org.testng.annotations.Test;

public class CustomerTest extends BaseServiceTest {
    @Test(description = "Verify that the CustomerService findAll method works does not throw an exception")
    public void verifyFindAllWork() {
        CustomerService customerService = new CustomerServiceImpl();
        customerService.findAll();
    }
}
