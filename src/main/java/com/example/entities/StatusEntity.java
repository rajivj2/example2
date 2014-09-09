package com.example.entities;

import java.io.Serializable;

public abstract class StatusEntity implements Serializable {

	
	public abstract void setID(int ID);

	
	public abstract int getID();

	
	public abstract void setUserId(int userId);

	
	public abstract int getUserId();
}