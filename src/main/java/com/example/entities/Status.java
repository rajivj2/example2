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
@Table(name = "status")
@NamedQueries({@NamedQuery
	(name = "findByUserId",
	query = "SELECT m FROM Status m WHERE m.userId = :userId")})
public class Status extends StatusEntity {

	private int ID;
	private int userId;
	
	public Status() {
		
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
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name = "userid", nullable = false)
	public int getUserId() {
		return userId;
	}
}