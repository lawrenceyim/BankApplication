package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.persistence.AccountRepository;
import com.solvd.bankapplication.persistence.impl.AccountRepositoryImpl;
import com.solvd.bankapplication.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
    }
}
