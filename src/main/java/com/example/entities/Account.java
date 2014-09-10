package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends AccountEntity {

	private int ID;
	private int accountId;
	
	public Account() {
		
	}

	public void setID(int ID) {
		this.ID = ID;	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	public int getID() {
		return ID;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "accountid", nullable = false)
	public int getAccountId() {
		return accountId;
	}
}