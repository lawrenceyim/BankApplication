package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.persistence.impl.PaymentDaoImpl;
import com.solvd.bankapplication.persistence.impl.PaymentDaoMybatisImpl;
import com.solvd.bankapplication.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private PaymentDao paymentDao;

    public PaymentServiceImpl(String mode) {
        if (mode.equals("mybatis")) {
            paymentDao = new PaymentDaoMybatisImpl();
        } else if (mode.equals("jdbc")) {
            paymentDao = new PaymentDaoImpl();
        }
    }

    @Override
    public void findAll() {
        List<Payment> payments = paymentDao.findAll();
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
