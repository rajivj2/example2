package com.example.persistence.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.entities.Status;
import com.example.persistence.dao.StatusDAO;

public class StatusJpaDAO extends GenericJpaDAO<Status> implements StatusDAO {

	private Logger logger = LoggerFactory.getLogger(StatusJpaDAO.class);

	public StatusJpaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Status findByUserId(int userId) {
		Status status = null;
		List<Status> list = getEntityManager().createNamedQuery("findByUserId").setParameter("userId", userId).getResultList();
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