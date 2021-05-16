package com.axis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.app.model.Trader;

public interface TraderRepository extends JpaRepository<Trader, Integer> {
	
	void deleteByAccNo(String accNo);
	Trader findByUsername(String username);
	Trader findByAccNo(String accNo);


}
