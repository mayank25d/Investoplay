package com.axis.app.service;

import java.util.List;

import com.axis.app.model.Stock;

import io.samco.client.model.HistoricalCandleResponse;
import io.samco.client.model.IntraDayCandleResponses;
import io.samco.client.model.MarketDepthResponse;

public interface StockService {
	
	public List<Stock> getAllStocks();
	public MarketDepthResponse getStockQuote(String stkSym);
	public HistoricalCandleResponse getIntraDayStockHistory(String stkSym); //, Date from);  // exchange by default is NSE and to date is the yesterday date
	public IntraDayCandleResponses getStockHistory(String stkSym);

}
