package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Account;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {
    List<Account> findByCustomer(long id);
}
