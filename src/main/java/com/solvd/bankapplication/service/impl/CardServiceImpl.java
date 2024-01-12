package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.CardDao;
import com.solvd.bankapplication.service.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class CardServiceImpl implements CardService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";

    private CardDao cardDao;

    public CardServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            cardDao = (CardDao) Class.forName(classPath + "CardDaoImpl").getConstructor().newInstance();
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
        List<Card> cards = cardDao.findAll();
        if (cards.isEmpty()) {
            logger.info("No cards found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s\n", "Card ID", "Card Type", "Account ID"));
        cards.stream().forEach(card -> {
            sb.append(String.format("%-30s%-30s%-30s\n", card.getCardID(), card.getCardType(), card.getAccountID()));
        });
        logger.info(sb.toString());
    }
}
