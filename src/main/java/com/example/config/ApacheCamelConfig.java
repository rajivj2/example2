package com.example.config;

import java.util.Properties;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import com.example.processor.AccountProcessor;
import com.example.processor.ContentEnricherProcessor;
import com.example.router.NotificationRouter;

@Configuration
public class ApacheCamelConfig extends SingleRouteCamelConfiguration implements InitializingBean {

	private static CamelContext camelContext;
	@Autowired
	private DAOFactoryConfig daoFactoryConfig;
	@Autowired
	private TransactionManagerConfig transactionManagerConfig;
	private Properties properties;
	
	public ApacheCamelConfig() throws Exception {
 		properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("camel.properties"));
	}
	
	@Bean(name = "context")
	protected CamelContext createCamelContext() throws Exception {
		return camelContext = new SpringCamelContext(getApplicationContext());
	}
	
	protected void setupCamelContext(CamelContext camelContext) throws Exception {
		camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent(properties.getProperty("activemq.location")));
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}
	
	@Bean
	@DependsOn(value = {"context"})
	public CamelContext stop() throws Exception {
		camelContext.stop();
		return camelContext;
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
	
	@Bean
	public AccountProcessor accountProcessor() throws Exception {
		AccountProcessor accountProcessor = new AccountProcessor(daoFactoryConfig.daoFactory().getStatusDAO());
		return accountProcessor;
	}
	
	@Bean
	public SpringTransactionPolicy defaultTransactionPolicy() throws Exception {
		SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
		springTransactionPolicy.setTransactionManager((PlatformTransactionManager) transactionManagerConfig.transactionManager());
		return springTransactionPolicy;
	}
}