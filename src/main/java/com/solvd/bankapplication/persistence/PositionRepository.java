package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepository {
    void create(Position position);

    Optional<Position> findById(long id);

    List<Position> findAll();

    void update(Position position);

    void deleteById(long id);
}
