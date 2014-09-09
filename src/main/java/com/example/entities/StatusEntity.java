package com.example.entities;

import java.io.Serializable;

/**
 * This class represents a StatusEntity.
 * @author Rajiv Jain
 */
public abstract class StatusEntity implements Serializable {

	/**
	 * This method sets the id.
	 * @param ID the ID.
	 */
	public abstract void setID(int ID);

	/**
	 * This method gets the id.
	 * @return the id.
	 */
	public abstract int getID();

	/**
	 * This method sets the user id.
	 * @param userId the userId.
	 */
	public abstract void setUserId(int userId);

	/**
	 * This method gets the user id.
	 * @return the user id.
	 */
	public abstract int getUserId();
}