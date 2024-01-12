package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Transfer;

import java.util.List;

public interface TransferDao extends BaseDao<Transfer> {
    List<Transfer> findAllByAccount(long id);
}
