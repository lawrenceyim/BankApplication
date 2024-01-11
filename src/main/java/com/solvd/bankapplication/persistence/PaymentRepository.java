package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Payment;

import java.util.List;

public interface PaymentRepository extends BasePersistence<Payment> {
    List<Payment> findAllByCard(long id);
}
