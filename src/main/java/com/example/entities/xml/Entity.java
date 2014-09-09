package com.example.entities.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "example")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity {

	@XmlElement(name = "remoteid")
	private String userId;
	
	public Entity() {
		
	}
	
	
	public String getUserId() {
		return userId;
	}

	
	public void setUserId(String userId) {
		this.userId = userId;
	}
}