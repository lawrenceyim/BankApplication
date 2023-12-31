package com.solvd.bankapplication.persistence;

import com.solvd.bankapplication.domain.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    void create(Branch branch);

    Optional<Branch> findById(long id);

    List<Branch> findAll();

    void update(Branch branch);

    void deleteById(long id);
}
