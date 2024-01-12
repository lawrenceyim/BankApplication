package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.service.CardService;
import com.solvd.bankapplication.utils.JdbcClassGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class CardServiceImpl implements CardService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private CardDao cardDao;

    public CardServiceImpl() {
        cardDao = JdbcClassGenerator.generateClassInstance("CardDaoImpl");
    }

    @Override
    public void findAll() {
        List<Card> cards = cardDao.findAll();
        if (cards.isEmpty()) {
            logger.info("No cards found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s" + System.lineSeparator(), "Card ID", "Card Type", "Account ID"));
        cards.stream().forEach(card -> {
            sb.append(String.format("%-30s%-30s%-30s" + System.lineSeparator(), card.getCardID(), card.getCardType(), card.getAccountID()));
        });
        logger.info(sb.toString());
    }
}
