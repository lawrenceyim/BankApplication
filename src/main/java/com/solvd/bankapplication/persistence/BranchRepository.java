package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    void create(Branch branch);

    void deleteById(long id);

    Optional<Branch> findById(long id);

    void update(Branch branch);

    List<Branch> findAll();
}
