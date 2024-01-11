package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Transfer;

import java.util.List;

public interface TransferRepository extends BasePersistence<Transfer> {
    List<Transfer> findAllByAccount(long id);
}
