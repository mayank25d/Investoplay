package com.axis.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.app.model.StockHolding;

public interface StockHoldingRepository extends JpaRepository<StockHolding, Integer> {
	
	List<StockHolding> findByTraderAccNo(String accNo);
	StockHolding findByTraderAccNoAndStkSym(String accNo, String stockSym);
	void deleteByTraderAccNoAndStkSym(String accNo, String stockSym);
}
