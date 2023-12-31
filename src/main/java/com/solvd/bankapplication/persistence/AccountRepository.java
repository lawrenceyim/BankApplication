package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void create(Account account);

    Optional<Account> findById(long id);

    List<Account> findAll();

    List<Account> findByCustomer(long id);

    void update(Account account);

    void deleteById(long id);
}
