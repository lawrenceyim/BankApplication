package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void create(Employee employee);

    Optional<Employee> findById(long id);

    List<Employee> findAll();

    void update(Employee employee);

    void deleteById(long id);
}
