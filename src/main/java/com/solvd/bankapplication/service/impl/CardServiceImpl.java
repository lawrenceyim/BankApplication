package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.persistence.CardRepository;
import com.solvd.bankapplication.persistence.impl.AccountRepositoryImpl;
import com.solvd.bankapplication.persistence.impl.AccountRepositoryMybatisImpl;
import com.solvd.bankapplication.persistence.impl.CardRepositoryImpl;
import com.solvd.bankapplication.persistence.impl.CardRepositoryMybatisImpl;
import com.solvd.bankapplication.service.CardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CardServiceImpl implements CardService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private CardRepository cardRepository;

    public CardServiceImpl(String mode) {
        if (mode.equals("mybatis")) {
            cardRepository = new CardRepositoryMybatisImpl();
        } else if (mode.equals("jdbc")) {
            cardRepository = new CardRepositoryImpl();
        }
    }

    @Override
    public void findAll() {
        List<Card> cards = cardRepository.findAll();
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
