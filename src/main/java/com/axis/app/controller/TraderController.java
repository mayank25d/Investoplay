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
import com.axis.app.model.Trader;
import com.axis.app.service.TraderService;


@RestController
@RequestMapping("api/trader")
public class TraderController {

	@Autowired
	TraderService service;

	@GetMapping("/admin/view")
	public List<Trader> viewAll() throws ResourceNotFoundException {
		return service.getAllTraders();
	}

	@PostMapping("/create")
	public Trader createNewTrader(@RequestBody Trader trader) throws Exception {
		return service.addTrader(trader);
	}

	@GetMapping("/find/id/{id}")
	public Trader findTrader(@PathVariable Integer id) throws ResourceNotFoundException {
		return service.getTraderInfo(id);
	}
	
	@GetMapping("/find/username/{username}")
	public Trader findTraderByUsername(@PathVariable String username) throws ResourceNotFoundException {
		return service.getTraderInfoByUsername(username);
	}

	@GetMapping("/find/accno/{accNo}")
	public Trader findTraderByAccNo(@PathVariable String accNo) throws ResourceNotFoundException {
		return service.getTraderInfoByAcc(accNo);
	}

	@DeleteMapping("/delete/id/{id}")
	public String deleteTraderData(@PathVariable Integer id) throws Exception {
		service.deleteTrader(id);
		return "Account is Deleted";
	}

	@DeleteMapping("/delete/accno/{accNo}")
	public String deleteTraderByAccNo(@PathVariable String accNo) throws Exception {
		service.deleteTraderByAcc(accNo);
		return "Account is Deleted";
	}
	
}
