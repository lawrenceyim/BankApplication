package com.solvd.bankapplication;

import com.solvd.bankapplication.service.BankService;
import com.solvd.bankapplication.service.impl.BankServiceImpl;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankTests extends BaseServiceTest {
    // This does not work at the moment. The service requires user input in the terminal
    // TODO: Implement a way to simulate the user input in the testcase
    @Test(description = "Verify that the findBankCustomerDetails method works does not throw an exception",
            enabled = false)
    public void verifyFindBankCustomerDetailsWork() {
        BankService bankService = new BankServiceImpl();

        String testInput = "1";
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        bankService.findBankCustomerDetails();

        System.setIn(System.in);
    }
}
