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
@Table(name = "employee")
public class Employee extends EmployeeEntity {

	private int ID;
	private String employeeName;
	
	public Employee() {
		
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
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@Column(name = "employeename", nullable = false)
	public String getEmployeeName() {
		return employeeName;
	}
}