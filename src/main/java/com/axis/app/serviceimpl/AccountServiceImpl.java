package com.axis.app.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.app.model.Transactions;
import com.axis.app.repository.AccountRepository;
import com.axis.app.repository.StockHoldingRepository;
import com.axis.app.repository.TraderRepository;
import com.axis.app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;
	
	@Autowired
	TraderRepository traderRepo;
	
	@Autowired
	StockHoldingRepository stkEntryRepo;
	
	@Override
	public Transactions createTransactions(String accNo, Transactions t) {
		String stkSym = t.getStkSym();
		t.setTrader(traderRepo.findByAccNo(accNo));
		t.setStkHolding(stkEntryRepo.findByTraderAccNoAndStkSym(accNo, stkSym));
		t.setDate(new Date());
		return repo.save(t);
	}

	@Override
	public List<Transactions> getTransactionByAcc(String accNo) {
		return repo.findByTraderAccNo(accNo);
	}

	@Override
	public List<Transactions> getTransactionByAccNDate(String accNo, Date date) {
		return repo.findByTraderAccNoAndDate(accNo, date);
	}

}
