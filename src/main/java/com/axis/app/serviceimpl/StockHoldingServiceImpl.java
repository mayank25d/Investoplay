package com.axis.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.app.model.StockHolding;
import com.axis.app.repository.StockHoldingRepository;
import com.axis.app.repository.TraderRepository;
import com.axis.app.service.StockHoldingService;

@Service
public class StockHoldingServiceImpl implements StockHoldingService {
	
	@Autowired
	StockHoldingRepository repo;
	
	@Autowired
	TraderRepository traderRepo;

	@Override
	public StockHolding createOrUpdateStockEntry(String accNo, StockHolding stocksEntry) {
		String stkSym = stocksEntry.getStkSym();
		StockHolding stockEntry = repo.findByTraderAccNoAndStkSym(accNo, stkSym);
		System.out.println(stockEntry);
		
		if(stockEntry == null) {
			stocksEntry.setTrader(traderRepo.findByAccNo(accNo));
			return repo.save(stocksEntry);
		} else {
			StockHolding updateStockEntry = stockEntry;
			updateStockEntry.setPrice(stocksEntry.getPrice());
			if(stocksEntry.getQty() == 0) {
				this.removeStockEntry(accNo, stkSym);
			} else {
				updateStockEntry.setQty(stocksEntry.getQty());	
			}
			
			return repo.save(updateStockEntry);
		}
	}

	@Override
	public void removeStockEntry(String accNo, String sym) {
		repo.deleteByTraderAccNoAndStkSym(accNo, sym);
	}

	@Override
	public List<StockHolding> getEntryByAcc(String accNo) {
		return repo.findByTraderAccNo(accNo);
	}

	@Override
	public StockHolding getEntryByAccNSym(String accNo, String sym) {
		System.out.println(accNo + sym);
		return repo.findByTraderAccNoAndStkSym(accNo, sym);
	}

}
