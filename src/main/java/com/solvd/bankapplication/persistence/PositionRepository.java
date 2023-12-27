package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepository {
    void create(Position position);

    void deleteById(long id);

    Optional<Position> findById(long id);

    void update(Position position);

    List<Position> findAll();
}
