package com.axis.app.service;

import java.util.List;

import com.axis.app.model.StockHolding;

public interface StockHoldingService {
	
	public StockHolding createOrUpdateStockEntry(String accNo, StockHolding stockEntry);
	public List<StockHolding> getEntryByAcc(String accNo); // search for orders by account number
	public StockHolding getEntryByAccNSym(String accNo, String sym); // search for orders by account number and stock symbols
	
	public void removeStockEntry(String accNo, String sym);

}
