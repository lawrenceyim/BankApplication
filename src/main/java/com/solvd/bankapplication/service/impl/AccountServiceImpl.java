package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.persistence.AccountRepository;
import com.solvd.bankapplication.persistence.impl.AccountRepositoryImpl;
import com.solvd.bankapplication.persistence.impl.AccountRepositoryMybatisImpl;
import com.solvd.bankapplication.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private AccountRepository accountRepository;

    public AccountServiceImpl(String mode) {
        if (mode.equals("mybatis")) {
            this.accountRepository = new AccountRepositoryMybatisImpl();
        } else if (mode.equals("jdbc")) {
            this.accountRepository = new AccountRepositoryImpl();
        }
    }

    @Override
    public void findAll() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            logger.info("No accounts found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s\n", "Account ID", "Customer ID", "Account Type", "Balance"));
        accounts.stream().forEach(account -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s\n", account.getAccountID(), account.getCustomerID(),
                    account.getAccountType(), account.getBalance()));
        });
        logger.info(sb.toString());
    }
}
