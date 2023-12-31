package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    void create(Payment payment);

    Optional<Payment> findById(long id);

    List<Payment> findAll();

    List<Payment> findAllByCard(long id);

    void update(Payment payment);

    void deleteById(long id);
}
