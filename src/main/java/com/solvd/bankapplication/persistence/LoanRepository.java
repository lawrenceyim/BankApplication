package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void create(Loan loan);

    void deleteById(long id);

    Optional<Loan> findById(long id);

    void update(Loan loan);

    List<Loan> findAll();
}
