package com.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.example.persistence.dao.StatusDAO;

@Transactional(value = "transactionManager")
public class AccountProcessor implements Processor {

	private StatusDAO statusDAO;
	private Logger logger;

	public AccountProcessor(StatusDAO statusDAO) {
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
		Message message = exchange.getIn().copy();
		exchange.setOut(message);
	}
}