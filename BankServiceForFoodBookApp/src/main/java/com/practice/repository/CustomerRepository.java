package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("from Customer  where mobile=?1")
	Customer getByMobile(Long mobile);

}
