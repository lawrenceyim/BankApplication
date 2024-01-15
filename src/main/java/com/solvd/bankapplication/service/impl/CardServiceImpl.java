package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.domain.Payment;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.persistence.PaymentDao;
import com.solvd.bankapplication.service.CardService;
import com.solvd.bankapplication.utils.ClassInstantiation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CardServiceImpl implements CardService {
    private final Logger OUTPUT_LOGGER = (Logger) LogManager.getLogger("Output");
    private final CardDao cardDao = ClassInstantiation.generateClassInstance("CardDaoImpl");
    private final PaymentDao paymentDao = ClassInstantiation.generateClassInstance("PaymentDaoImpl");

    @Override
    public void findAllCardsAndPayments() {
        StringBuilder sb = new StringBuilder();
        List<Card> cards = cardDao.findAll();
        cards.stream()
                .filter(card -> !paymentDao.findAllByCard(card.getCardID()).isEmpty())
                .forEach(card -> {
                    sb.append("Card ID: ").append(card.getCardID()).append(System.lineSeparator());
                    sb.append("Card Type: ").append(card.getCardType()).append(System.lineSeparator());
                    sb.append(String.format("%-30s%-30s%-30s%-30s%-30s", "Payment ID", "Card ID", "Company",
                            "Date", "Amount"));
                    sb.append(System.lineSeparator());
                    List<Payment> payments = paymentDao.findAllByCard(card.getCardID());
                    payments.stream().forEach(payment -> {
                        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s",
                                payment.getPaymentID(),
                                payment.getCardID(),
                                payment.getCompanyName(),
                                payment.getDate(),
                                payment.getAmount()));
                        sb.append(System.lineSeparator());
                    });
                });
        OUTPUT_LOGGER.info(sb.toString());
    }
}
