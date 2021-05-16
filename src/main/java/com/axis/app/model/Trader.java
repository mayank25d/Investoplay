package com.axis.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trader", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "accNo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader {
	// this will also create a demat account
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int traderId;
	
	@NotBlank
	@Size(min=3, max=100)
	private String username;
	
	// auto generated fields
	private String accNo; // a unique account number
	
	@NotNull // NotBlank does not support double
	private double balance = 100000.00;
	
	// model relation
//	@OneToMany(mappedBy="trader")
//	@Fetch(FetchMode.JOIN)
//	private Set<Transactions> transactions;
	
	/*
	 * @OneToMany(mappedBy="trader")
	 * @Fetch(FetchMode.JOIN) private Set<PurchaseOrder> pchaseOrders;
	 */
	
//	@OneToMany(mappedBy="trader")
//	@Fetch(FetchMode.JOIN)
//	private Set<StockHolding> stockHolding;
	
}
