package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.bin.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void create(Account account);

    void deleteById(long id);

    Optional<Account> findById(long id);

    void update(Account account);

    List<Account> findAll();

    List<Account> findByCustomer(long id);
}
