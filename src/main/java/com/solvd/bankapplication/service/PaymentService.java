package com.solvd.bankapplication.service;

import com.solvd.bankapplication.bin.Payment;

import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);

    List<Payment> retrieveAll();
}
