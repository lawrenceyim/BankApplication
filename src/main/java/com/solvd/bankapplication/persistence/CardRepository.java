package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    void create(Card card);

    Optional<Card> findById(long id);

    List<Card> findAll();

    List<Card> findAllByAccount(long id);

    void update(Card card);

    void deleteById(long id);
}
