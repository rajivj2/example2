package com.example.entities;

import java.io.Serializable;

public abstract class EmployeeEntity implements Serializable {

	
	public abstract void setID(int ID);

	
	public abstract int getID();

	
	public abstract void setEmployeeName(String employeeName);

	
	public abstract String getEmployeeName();
}