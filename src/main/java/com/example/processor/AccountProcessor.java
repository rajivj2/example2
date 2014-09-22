package com.example.processor;

import javax.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.example.persistence.dao.StatusDAO;

@Transactional(value = "transactionManagerAccount")
public class AccountProcessor implements Processor {
	
	@Resource
	private StatusDAO statusDAO;
	private Logger logger;
	
	public AccountProcessor(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
		logger = LoggerFactory.getLogger(AccountProcessor.class);
	}
	
	AccountProcessor() {
		super();
	}

	/**
	 * This method processes the data by aggregating the data.
	 * @param exchange the exchange.
	 * @throws Exception when an Exception occurs.
	 */	
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn().copy();
		exchange.setOut(message);
	}
}