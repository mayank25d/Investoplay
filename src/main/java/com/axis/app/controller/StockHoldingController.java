package com.axis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.app.exception.ResourceNotFoundException;
import com.axis.app.model.StockHolding;
import com.axis.app.service.StockHoldingService;
import com.axis.app.service.TraderService;

@RestController
@RequestMapping("api/stockHolding")
public class StockHoldingController {
	
	@Autowired
	StockHoldingService service;
	
	@GetMapping("/view/{accNo}")
	public List<StockHolding> viewAll(@PathVariable String accNo) throws ResourceNotFoundException {
		return service.getEntryByAcc(accNo);
	}
	
	@GetMapping("/view/byStock/{accNo}/{stkSym}")
	public StockHolding viewByAccAndSym(@PathVariable String accNo, @PathVariable String stkSym) throws ResourceNotFoundException {
		return service.getEntryByAccNSym(accNo, stkSym);
	}
	
	@PostMapping("/{accNo}/createOrUpdate")
	public StockHolding createOrUpdateStockEntry(@PathVariable String accNo, @RequestBody StockHolding stockEntry) {
		return service.createOrUpdateStockEntry(accNo, stockEntry);
	}
	
	@DeleteMapping("/delete/stockEntry/{accNo}/{stkSym}")
	public String deleteStockEntry(@PathVariable String accNo, @PathVariable String stkSym) {
		service.removeStockEntry(accNo, stkSym);
		return "StockEntry Deleted";
	}

}
