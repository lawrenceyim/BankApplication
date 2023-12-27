package com.solvd.bankapplication.service;

import com.solvd.bankapplication.bin.Payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment create(Payment payment);
    List<Payment> retrieveAll();
}
