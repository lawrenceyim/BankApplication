package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    void create(Card card);

    void deleteById(long id);

    Optional<Card> findById(long id);

    void update(Card card);

    List<Card> findAll();

    List<Card> findAllByAccount(long id);
}
