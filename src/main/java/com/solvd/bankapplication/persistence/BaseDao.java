package com.solvd.bankapplication.persistence;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    void create(T t);

    Optional<T> findById(long id);

    List<T> findAll();

    void update(T t);

    void deleteById(long id);
}
