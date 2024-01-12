package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Card;

import java.util.List;

public interface CardDao extends BaseDao<Card> {
    List<Card> findAllByAccount(long id);
}
