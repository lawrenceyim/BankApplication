package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Account;

import java.util.List;

public interface AccountRepository extends BasePersistence<Account> {
    List<Account> findByCustomer(long id);
}
