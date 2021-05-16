package com.axis.app.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stockHolding")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockHolding {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "traderId")
	private Trader trader;
	
//	@OneToMany(mappedBy="stkHolding")
//	@Fetch(FetchMode.JOIN)
//	private Set<Transactions> transactions;
	
	// about stock
	private String stkSym;
	private String productType;
	private String orderType;
	private double limitBuyPrice;
	private double limitSellPrice;
	
	private double price;
	private int qty;
	
}
