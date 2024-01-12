package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.service.PaymentService;
import com.solvd.bankapplication.utils.JdbcClassGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private PaymentDao paymentDao;

    public PaymentServiceImpl() {
        paymentDao = JdbcClassGenerator.generateClassInstance("PaymentDaoImpl");
    }

    @Override
    public void findAll() {
        List<Payment> payments = paymentDao.findAll();
        if (payments.isEmpty()) {
            logger.info("No payments found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Payment ID", "Company", "Date", "Amount", "Card ID"));
        payments.stream().forEach(payment -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), payment.getPaymentID(), payment.getCompanyName(),
                    payment.getDate(), payment.getAmount(), payment.getCardID()));
        });
        logger.info(sb.toString());
    }
}
