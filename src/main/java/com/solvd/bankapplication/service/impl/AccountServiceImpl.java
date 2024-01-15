package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.service.AccountService;
import com.solvd.bankapplication.utils.ClassInstantiation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private final AccountDao accountDao = ClassInstantiation.generateClassInstance("AccountDaoImpl");

    @Override
    public void findAllAccounts() {
        List<Account> accounts = accountDao.findAll();
        if (accounts.isEmpty()) {
            OUTPUT_LOGGER.info("No accounts found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Account ID", "Customer ID",
                "Account Type", "Balance"));
        accounts.stream().forEach(account -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), account.getAccountID(),
                    account.getCustomerID(), account.getAccountType(), account.getBalance()));
        });
        OUTPUT_LOGGER.info(sb.toString());
    }
}
