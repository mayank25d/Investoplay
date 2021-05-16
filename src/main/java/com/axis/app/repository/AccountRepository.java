package com.axis.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.app.model.Transactions;

public interface AccountRepository extends JpaRepository<Transactions, Integer> {

	List<Transactions> findByTraderAccNo(String accNo);
	
	List<Transactions> findByTraderAccNoAndDate(String accNo, Date date);
}
