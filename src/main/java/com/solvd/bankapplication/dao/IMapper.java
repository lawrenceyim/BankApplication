package com.solvd.bankapplication.dao;

import java.sql.ResultSet;

public interface IMapper {
    void executeQuery(String query);

    ResultSet retrieveResult();
}
