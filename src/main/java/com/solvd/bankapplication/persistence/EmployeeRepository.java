package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void create(Employee employee);

    void deleteById(long id);

    Optional<Employee> findById(long id);

    void update(Employee employee);

    List<Employee> findAll();
}
