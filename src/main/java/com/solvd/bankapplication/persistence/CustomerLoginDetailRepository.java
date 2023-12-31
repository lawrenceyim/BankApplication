package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.CustomerLoginDetail;

import java.util.List;
import java.util.Optional;

public interface CustomerLoginDetailRepository {
    void create(CustomerLoginDetail customerLoginDetail);

    Optional<CustomerLoginDetail> findById(long id);

    List<CustomerLoginDetail> findAll();

    void update(CustomerLoginDetail customerLoginDetail);

    void deleteById(long id);
}
