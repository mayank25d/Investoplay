package com.axis.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.app.exception.ResourceNotFoundException;
import com.axis.app.model.Transactions;
import com.axis.app.service.AccountService;

@RestController
@RequestMapping("api/trans")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/view/{accNo}")
	public List<Transactions> viewAllByAcc(@PathVariable String accNo) throws ResourceNotFoundException {
		return service.getTransactionByAcc(accNo);
	}
	
	@GetMapping("/view/{accNo}/{date}")
	public List<Transactions> viewAllByAccNDate(@PathVariable String accNo, @PathVariable Date date) throws ResourceNotFoundException {
		return service.getTransactionByAccNDate(accNo, date);
	}
	
	@PostMapping("/create/{accNo}")
	public Transactions createNewTransaction(@PathVariable String accNo, @RequestBody Transactions t) throws Exception {
		return service.createTransactions(accNo, t);
	}

}
