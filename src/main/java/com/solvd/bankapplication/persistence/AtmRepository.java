package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Atm;

import java.util.List;
import java.util.Optional;

public interface AtmRepository {
    void create(Atm atm);

    void deleteById(long id);

    Optional<Atm> findById(long id);

    void update(Atm atm);

    List<Atm> findAll();
}
