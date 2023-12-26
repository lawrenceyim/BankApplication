package com.solvd.bankapplication.persistence;

import java.sql.ResultSet;

public interface AccountsRepository {
    int create();
    int updateByAccountId(int id);
    int deleteByAccountId(int id);
    ResultSet findByAccountId(int id);
    ResultSet findByCustomerId();
}
