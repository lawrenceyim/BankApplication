package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.bin.Payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    void create(Payment payment);
    void deleteById(long id);
    Optional<Payment> findById(long id);
    void update(Payment payment);
    List<Payment> findAll();
}
