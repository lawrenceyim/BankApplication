package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";
    private PaymentDao paymentDao;

    public PaymentServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            paymentDao = (PaymentDao) Class.forName(classPath + "PaymentDaoImpl").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Payment ID", "Company", "Date", "Amount", "Card ID"));
        payments.stream().forEach(payment -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), payment.getPaymentID(), payment.getCompanyName(),
                    payment.getDate(), payment.getAmount(), payment.getCardID()));
        });
        logger.info(sb.toString());
    }
}
