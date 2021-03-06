package com.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Status;
import com.example.entities.xml.Entity;
import com.example.persistence.dao.StatusDAO;

@Transactional(value = "transactionManager")
public class ContentEnricherProcessor implements Processor {

	private StatusDAO statusDAO;
	private Logger logger = LoggerFactory.getLogger(ContentEnricherProcessor.class);

	public ContentEnricherProcessor(StatusDAO statusDAO) {
		super();
		this.statusDAO = statusDAO;
	}

	/**
	 * This method processes the data by aggregating the data.
	 * @param exchange the exchange.
	 * @throws Exception when an Exception occurs.
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		Entity entity = (Entity) message.getBody();
		Status status = process(entity);
		message.setBody(status);
		exchange.setOut(message);
	}

	Status process(Entity entity) {
		Status status = null;
		if(entity.getUserId() != null) {
			logger.info("About to find userId");
			status = statusDAO.findByUserId(Integer.parseInt(entity.getUserId()));
			logger.info("Finished finding by remoteId");
		}
		status.setUserId(11);
		statusDAO.update(status);
		return status;
	}
}