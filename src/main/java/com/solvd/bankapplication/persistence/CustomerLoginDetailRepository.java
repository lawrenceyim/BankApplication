package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.CustomerLoginDetail;

import java.util.List;
import java.util.Optional;

public interface CustomerLoginDetailRepository {
    void create(CustomerLoginDetail customerLoginDetail);

    void deleteById(long id);

    Optional<CustomerLoginDetail> findById(long id);

    void update(CustomerLoginDetail customerLoginDetail);

    List<CustomerLoginDetail> findAll();
}
