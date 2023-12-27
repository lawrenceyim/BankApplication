package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    void create(Location location);

    void deleteById(long id);

    Optional<Location> findById(long id);

    void update(Location location);

    List<Location> findAll();
}
