package com.example.datasource.jms;

import javax.jms.ConnectionFactory;
import org.apache.activemq.spring.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jms.AtomikosConnectionFactoryBean;

@Configuration
public class JmsConnectionFactoryConfig {

	@Autowired
	JmsConfig jmsConfig;

	public JmsConnectionFactoryConfig() {
		super();
	}

	@Bean
	public ConnectionFactory jmsConnectionFactory() {
		final ActiveMQXAConnectionFactory xaConnectionFactory = new ActiveMQXAConnectionFactory();
		xaConnectionFactory.setBrokerURL(jmsConfig.getLocation());
		final AtomikosConnectionFactoryBean connectionFactory = new AtomikosConnectionFactoryBean();
		connectionFactory.setLocalTransactionMode(false);
		connectionFactory.setXaConnectionFactory(xaConnectionFactory);
		connectionFactory.setUniqueResourceName("jmsConnectionFactory");
		connectionFactory.setMaxPoolSize(100);
		return connectionFactory;
	}
}
