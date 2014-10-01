package com.example.persistence.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.entities.Status;
import com.example.persistence.dao.StatusDAO;

public class StatusJpaDAO extends GenericJpaDAO<Status> implements StatusDAO {

	private Logger logger = LoggerFactory.getLogger(StatusJpaDAO.class);
	
	public StatusJpaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Status findByUserId(int userId) {
		Status status = null;
		List<Status> list = null;
		logger.info("About to create named query");
		Query query = getEntityManager().createNamedQuery("findByUserId");
		logger.info("Query created");
		query.setParameter("userId", userId);
		logger.info("userId set");
		logger.info("getting resultlist");
		list = query.getResultList();
		if(list.size() > 0) {
			logger.info("fetched resultset :" + list.get(0));
		}
		else {
			logger.info("no result");
		}
		if(list.isEmpty()) {
			status = null;
		}
		else {
			status = list.get(0);
		}
		return status;
	}
}