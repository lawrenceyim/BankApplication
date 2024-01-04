package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.CardRepository;
import com.solvd.bankapplication.persistence.PaymentRepository;
import com.solvd.bankapplication.persistence.impl.PaymentRepositoryImpl;
import com.solvd.bankapplication.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl() {
         paymentRepository = new PaymentRepositoryImpl();
    }
    @Override
    public void findAll() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            logger.info("No payments found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", "Payment ID", "Company", "Date", "Amount", "Card ID"));
        payments.stream().forEach(payment -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", payment.getPaymentID(), payment.getCompanyName(),
                    payment.getDate(), payment.getAmount(), payment.getCardID()));
        });
        logger.info(sb.toString());
    }
}
