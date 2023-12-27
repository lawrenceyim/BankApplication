package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void create(Customer customer);

    void deleteById(long id);

    Optional<Customer> findById(long id);

    void update(Customer customer);

    List<Customer> findAll();
}
