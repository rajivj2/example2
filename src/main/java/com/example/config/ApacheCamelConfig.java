package com.example.config;

import java.util.Properties;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.osgi.SpringCamelContextFactory;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.processor.ContentEnricherProcessor;
import com.example.router.NotificationRouter;

@Configuration
public class ApacheCamelConfig extends SingleRouteCamelConfiguration implements InitializingBean {

	@Autowired
	private DAOFactoryConfig daoFactoryConfig;
	private Properties properties;
	
	public ApacheCamelConfig() throws Exception {
 		properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("camel.properties"));
	}
	
	@Bean(name = "context")
	protected CamelContext createCamelContext() throws Exception {
		SpringCamelContextFactory factory = new SpringCamelContextFactory();
		factory.setApplicationContext(getApplicationContext());
		return factory.createContext();
	}
	
	protected void setupCamelContext(CamelContext camelContext) throws Exception {
		camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent(properties.getProperty("activemq.location")));
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}
	
	@Bean
	public CamelContext stop() throws Exception {
		CamelContext context = createCamelContext();
		context.stop();
		return context;
	}
	
	@Bean
	public RouteBuilder route() {
		RouteBuilder deliveryNotificationRouter = new NotificationRouter();
		return deliveryNotificationRouter;
	}
	
	@Bean
	public ContentEnricherProcessor contentEnricherProcessor() throws Exception {
		ContentEnricherProcessor contentEnricherProcessor = new ContentEnricherProcessor(daoFactoryConfig.daoFactory().getStatusDAO());
		return contentEnricherProcessor;
	}
	
	/** @Bean
	public SpringTransactionPolicy defaultTransactionPolicy() throws Exception {
		SpringTransactionPolicy transactionPolicy = new SpringTransactionPolicy();
		transactionPolicy.setTransactionManager((PlatformTransactionManager) transactionManagerConfig.transactionManager());
		return transactionPolicy;
	} */
}