package com.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("from Transaction order by tid desc")
	List<Transaction> fetchTransactions();
}
