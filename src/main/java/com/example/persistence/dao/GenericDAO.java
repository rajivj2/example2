package com.example.persistence.dao;

public interface GenericDAO<T> {

	public T save(T t);

	public T update(T t);
}