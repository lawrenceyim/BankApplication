package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.service.AccountService;
import com.solvd.bankapplication.utils.JdbcClassGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class AccountServiceImpl implements AccountService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = JdbcClassGenerator.generateClassInstance("AccountDaoImpl");
    }

    @Override
    public void findAll() {
        List<Account> accounts = accountDao.findAll();
        if (accounts.isEmpty()) {
            logger.info("No accounts found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Account ID", "Customer ID",
                "Account Type", "Balance"));
        accounts.stream().forEach(account -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), account.getAccountID(),
                    account.getCustomerID(), account.getAccountType(), account.getBalance()));
        });
        logger.info(sb.toString());
    }
}
