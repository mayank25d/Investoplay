package com.axis.app.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.app.model.Stock;
import com.axis.app.repository.StockRepository;
import com.axis.app.service.StockService;

import io.samco.client.ApiException;
import io.samco.client.SamcoConstants;
import io.samco.client.api.HistoricalCandleDataApi;
import io.samco.client.api.IntraDayCandleDataApi;
import io.samco.client.api.QuoteApi;
import io.samco.client.api.UserLoginApi;
import io.samco.client.model.HistoricalCandleResponse;
import io.samco.client.model.IntraDayCandleResponses;
import io.samco.client.model.LoginRequest;
import io.samco.client.model.LoginResponse;
import io.samco.client.model.MarketDepthResponse;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository repo;
	
	public String xSessionToken = StockRepository.stockApiCall() ;
	
	@Override
	public List<Stock> getAllStocks() {
		return repo.findAll();
	}

	@Override
	public MarketDepthResponse getStockQuote(String stockSym) {
		QuoteApi qapi = new QuoteApi();
		MarketDepthResponse quote = null;
		System.out.println("------------------------"+xSessionToken+"---------------------------");
		try {
			quote = qapi.getQuote(xSessionToken, stockSym, SamcoConstants.EXCHANGE_NSE);
		} catch (ApiException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return quote;
	}
	
	@Override
	public HistoricalCandleResponse getIntraDayStockHistory(String stockSym) {
		HistoricalCandleDataApi hDataApi = new HistoricalCandleDataApi();
		HistoricalCandleResponse hData = null;
		try {
			hData = hDataApi.getHistoricalCandleData(xSessionToken, 
					stockSym, "2019-01-01", SamcoConstants.EXCHANGE_NSE, "2020-02-01");
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hData;
	}

	@Override
	public IntraDayCandleResponses getStockHistory(String stockSym) {
		IntraDayCandleDataApi iDayApi = new IntraDayCandleDataApi();
		IntraDayCandleResponses iDayData = null;
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);  
		LocalDateTime dateTime1 = LocalDateTime.now().minusMonths(1);
		LocalDateTime dateTime2 = LocalDateTime.now().minusMonths(1).minusHours(2);
		String currentDateTime = dateTime1.format(format);
		String earlyDateTime = dateTime2.format(format);
		
		try {
			iDayData = iDayApi.getIntradayCandleData(xSessionToken, 
					stockSym, earlyDateTime, SamcoConstants.EXCHANGE_NSE, currentDateTime);
		} catch (ApiException e) {
			try {
				iDayData = iDayApi.getIntradayCandleData(xSessionToken, 
						stockSym, "2021-04-13 11:00:00", SamcoConstants.EXCHANGE_NSE, "2021-04-13 13:00:00");
			} catch (ApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e);
			}
		}
		return iDayData;
	}
	
}
