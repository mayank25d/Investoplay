package com.axis.app.service;

import java.util.Date;
import java.util.List;

import com.axis.app.model.Transactions;

public interface AccountService {
	
	public Transactions createTransactions(String accno, Transactions t);
	public List<Transactions> getTransactionByAcc(String accNo);
	public List<Transactions> getTransactionByAccNDate(String accNo, Date date);

}
