package com.ifsc.entity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ifsc.entity.Branches;

public interface BranchRepository extends CrudRepository<Branches, String> {

	Branches findByIfsc(String ifsc);

	Page<Branches> findAllByBankIdAndCity(Long bankId, String city, Pageable pageable);
}
