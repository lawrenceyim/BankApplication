package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository {
    void create(Business business);

    Optional<Business> findById(long id);

    List<Business> findAll();

    void update(Business business);

    void deleteById(long id);
}
