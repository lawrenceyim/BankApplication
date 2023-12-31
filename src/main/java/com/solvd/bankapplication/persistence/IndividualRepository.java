package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Individual;

import java.util.List;
import java.util.Optional;

public interface IndividualRepository {
    void create(Individual individual);

    Optional<Individual> findById(long id);

    List<Individual> findAll();

    void update(Individual individual);

    void deleteById(long id);
}
