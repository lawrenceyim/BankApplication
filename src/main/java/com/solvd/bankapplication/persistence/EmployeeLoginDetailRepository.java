package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.EmployeeLoginDetail;

import java.util.List;
import java.util.Optional;

public interface EmployeeLoginDetailRepository {
    void create(EmployeeLoginDetail employeeLoginDetail);

    Optional<EmployeeLoginDetail> findById(long id);

    List<EmployeeLoginDetail> findAll();

    void update(EmployeeLoginDetail employeeLoginDetail);

    void deleteById(long id);
}
