package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Payment;

import java.util.List;

public interface PaymentDao extends BaseDao<Payment> {
    List<Payment> findAllByCard(long id);
}
