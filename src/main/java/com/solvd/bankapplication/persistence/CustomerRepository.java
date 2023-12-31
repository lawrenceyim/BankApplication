package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void create(Customer customer);

    Optional<Customer> findById(long id);

    List<Customer> findAll();

    void update(Customer customer);

    void deleteById(long id);
}
