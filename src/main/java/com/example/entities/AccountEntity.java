package com.example.entities;

import java.io.Serializable;

public abstract class AccountEntity implements Serializable {

	
	public abstract void setID(int ID);

	
	public abstract int getID();

	
	public abstract void setAccountId(int accountId);

	
	public abstract int getAccountId();
}