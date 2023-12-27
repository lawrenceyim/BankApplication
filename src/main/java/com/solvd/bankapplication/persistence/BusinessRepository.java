package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.bin.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository {
    void create(Business business);

    void deleteById(long id);

    Optional<Business> findById(long id);

    void update(Business business);

    List<Business> findAll();
}
