package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.bin.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferRepository {
    void create(Transfer transfer);

    void deleteById(long id);

    Optional<Transfer> findById(long id);

    void update(Transfer transfer);

    List<Transfer> findAll();

    List<Transfer> findAllByAccount(long id);
}
