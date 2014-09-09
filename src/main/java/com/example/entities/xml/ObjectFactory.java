package com.example.entities.xml;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public Entity createEntity() {
		return new Entity();
	}
}