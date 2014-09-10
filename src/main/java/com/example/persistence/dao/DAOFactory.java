package com.example.persistence.dao;

public abstract class DAOFactory {
	
	public abstract StatusDAO getStatusDAO();
	public abstract AccountDAO getAccountDAO();
}