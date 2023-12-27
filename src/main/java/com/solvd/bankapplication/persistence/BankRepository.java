package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    void create(Bank bank);

    void deleteById(long id);

    Optional<Bank> findById(long id);

    void update(Bank bank);

    List<Bank> findAll();

}
