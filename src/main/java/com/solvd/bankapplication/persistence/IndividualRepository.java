package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Individual;

import java.util.List;
import java.util.Optional;

public interface IndividualRepository {
    void create(Individual individual);

    void deleteById(long id);

    Optional<Individual> findById(long id);

    void update(Individual individual);

    List<Individual> findAll();
}
