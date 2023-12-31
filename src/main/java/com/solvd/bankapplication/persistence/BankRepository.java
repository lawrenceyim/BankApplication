package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    void create(Bank bank);

    Optional<Bank> findById(long id);

    List<Bank> findAll();

    void update(Bank bank);

    void deleteById(long id);
}
