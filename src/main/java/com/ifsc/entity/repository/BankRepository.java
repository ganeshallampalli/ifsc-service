package com.ifsc.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ifsc.entity.Banks;

@Repository
public interface BankRepository extends CrudRepository<Banks, Long> {

	Banks findByName(String name);

}
