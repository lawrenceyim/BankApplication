package com.solvd.bankapplication.service;

import com.solvd.bankapplication.domain.Payment;

import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);

    List<Payment> retrieveAll();
}
