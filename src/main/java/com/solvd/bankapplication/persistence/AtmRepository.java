package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Atm;

import java.util.List;
import java.util.Optional;

public interface AtmRepository {
    void create(Atm atm);

    Optional<Atm> findById(long id);

    List<Atm> findAll();

    void update(Atm atm);

    void deleteById(long id);

}
