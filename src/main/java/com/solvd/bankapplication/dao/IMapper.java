package com.solvd.bankapplication.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapper {
    void executeQuery(String query);
    ResultSet retrieveResult();
}
