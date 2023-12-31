package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void create(Loan loan);

    Optional<Loan> findById(long id);

    List<Loan> findAll();

    void update(Loan loan);

    void deleteById(long id);
}
