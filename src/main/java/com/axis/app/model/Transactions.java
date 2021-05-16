package com.axis.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	// About Stock
	private String stkSym;
	private double price;
	private int qty;
	private Date date;
	private boolean type; //debit = 0 or credit = 1

	// Join Model
	@ManyToOne
	@JoinColumn(name="traderId")
	private Trader trader;
	
	@ManyToOne
	@JoinColumn(name="stkHoldingId")
	private StockHolding stkHolding;
	
}
