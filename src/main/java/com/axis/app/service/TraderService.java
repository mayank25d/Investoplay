package com.axis.app.service;

import java.util.List;

import com.axis.app.model.Trader;

public interface TraderService {
	
	public List<Trader> getAllTraders();
	public Trader addTrader(Trader trader);
	public void deleteTrader(int id);
	public Trader getTraderInfo(int id);
	void deleteTraderByAcc(String accNo);
	public Trader getTraderInfoByAcc(String accNo);
	public Trader getTraderInfoByUsername(String username);
	

}
