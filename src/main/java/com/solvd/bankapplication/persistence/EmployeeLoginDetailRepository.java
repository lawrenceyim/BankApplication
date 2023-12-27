package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.EmployeeLoginDetail;

import java.util.List;
import java.util.Optional;

public interface EmployeeLoginDetailRepository {
    void create(EmployeeLoginDetail employeeLoginDetail);

    void deleteById(long id);

    Optional<EmployeeLoginDetail> findById(long id);

    void update(EmployeeLoginDetail employeeLoginDetail);

    List<EmployeeLoginDetail> findAll();
}
