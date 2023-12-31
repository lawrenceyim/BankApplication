package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    void create(Location location);

    Optional<Location> findById(long id);

    List<Location> findAll();

    void update(Location location);

    void deleteById(long id);
}
