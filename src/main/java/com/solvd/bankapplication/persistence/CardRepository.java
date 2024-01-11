package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Card;

import java.util.List;

public interface CardRepository extends BasePersistence<Card> {
    List<Card> findAllByAccount(long id);
}
