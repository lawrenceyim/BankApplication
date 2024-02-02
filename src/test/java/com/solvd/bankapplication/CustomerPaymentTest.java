package com.solvd.bankapplication;

import com.solvd.bankapplication.service.CustomerPaymentsService;
import com.solvd.bankapplication.service.impl.CustomerPaymentsServiceImpl;
import org.testng.annotations.Test;

public class CustomerPaymentTest extends BaseServiceTest {
    // This does not work at the moment. The service requires user input in the terminal
    // TODO: Implement a way to simulate the user input in the testcase
    @Test(description = "Verify that the findAllCustomerCardPayments method works does not throw an exception",
            enabled = false)
    public void verifyFindAllCustomerCardPaymentsWork() {
        CustomerPaymentsService customerPaymentsService = new CustomerPaymentsServiceImpl();
        customerPaymentsService.findAllCustomerCardPayments();
    }
}
