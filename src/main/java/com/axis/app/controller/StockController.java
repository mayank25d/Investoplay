package com.axis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.app.exception.ResourceNotFoundException;
import com.axis.app.model.Stock;
import com.axis.app.service.StockService;

import io.samco.client.model.IntraDayCandleResponses;
import io.samco.client.model.MarketDepthResponse;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	StockService service;

	@GetMapping("/view")
	public List<Stock> viewAllStocks() throws ResourceNotFoundException {
		return service.getAllStocks();
	}

	@GetMapping("/quote/{stkSym}")
	public MarketDepthResponse viewStockQuote(@PathVariable String stkSym) throws ResourceNotFoundException {
		MarketDepthResponse quote = service.getStockQuote(stkSym);
		return quote;
	}

	@GetMapping("/intra-day-history/{stkSym}")
	public IntraDayCandleResponses viewStockHisotry(@PathVariable String stkSym) throws ResourceNotFoundException {
		IntraDayCandleResponses his = service.getStockHistory(stkSym);
		return his;
	}
	

}
