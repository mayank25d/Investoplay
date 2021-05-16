package com.axis.app.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.app.model.Trader;
import com.axis.app.repository.TraderRepository;
import com.axis.app.service.TraderService;

@Service

public class TraderServiceImpl implements TraderService {

	@Autowired
	TraderRepository repo;

	@Override
	@Transactional
	public List<Trader> getAllTraders() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public Trader addTrader(Trader trader) {
		return repo.save(trader);
	}

	@Override
	@Transactional
	public void deleteTrader(int id) {
		repo.deleteById(id);		
	}
	
	@Override
	@Transactional
	public void deleteTraderByAcc(String accNo) {
		repo.deleteByAccNo(accNo);
	}

	@Override
	@Transactional
	public Trader getTraderInfo(int id) {
		return repo.findById(id).get();
	}
	
	@Override
	@Transactional
	public Trader getTraderInfoByAcc(String accNo) {
		return repo.findByAccNo(accNo);
	}

	@Override
	@Transactional
	public Trader getTraderInfoByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	
}
