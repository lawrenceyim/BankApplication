package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferRepository {
    void create(Transfer transfer);

    Optional<Transfer> findById(long id);

    List<Transfer> findAll();

    List<Transfer> findAllByAccount(long id);

    void update(Transfer transfer);

    void deleteById(long id);

}
