package com.solvd.bankapplication;

import com.solvd.bankapplication.service.AccountService;
import com.solvd.bankapplication.service.impl.AccountServiceImpl;
import org.testng.annotations.Test;

public class AccountTest extends BaseServiceTest {
    @Test(description = "Verify that the findAllAccounts method does not throw an exception")
    public void verifyFindAllAccountsWork() {
        AccountService accountService = new AccountServiceImpl();
        accountService.findAllAccounts();

    }
}
