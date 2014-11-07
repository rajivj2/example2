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
import com.example.persistence.dao.StatusDAO;
import com.example.processor.AccountProcessor;
import com.example.processor.ContentEnricherProcessor;
import com.example.processor.router.NotificationRouter;
import com.example.transactionmanager.DefaultTransactionManagerConfig;

@Configuration
//@ManagedResource(objectName = "bean:name=apacheCamelConfig")
//@EnableMBeanExport(server = "mBeanServer")
public class ApacheCamelConfig extends SingleRouteCamelConfiguration implements InitializingBean {

	private static CamelContext camelContext;
	@Autowired
	private DefaultTransactionManagerConfig defaultTransactionManagerConfig;
	@Autowired
	private StatusDAO statusDAO;
	private Properties properties;

	public ApacheCamelConfig() throws Exception {
 		properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("camel.properties"));
	}

//	@ManagedOperation
	@Override
	@Bean(name = "context")
	protected CamelContext createCamelContext() throws Exception {
		camelContext = new SpringCamelContext(getApplicationContext());
		camelContext.setAutoStartup(true);
//		return camelContext = new SpringCamelContext(getApplicationContext());
		return camelContext;
	}

//	@ManagedOperation
//	@ManagedOperationParameter(name = "route", description = "i")
	public void start(String route) throws Exception {
		camelContext.startRoute(route);
	}

	@Override
	protected void setupCamelContext(CamelContext camelContext) throws Exception {
		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent(properties.getProperty("activemq.location"));
		activeMQComponent.setTransacted(true);
		activeMQComponent.setTransactionManager(defaultTransactionManagerConfig.transactionManager());
		camelContext.addComponent("activemq", activeMQComponent);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

//	@Bean
//	public MBeanServer mBeanServer() {
//		MBeanServerFactoryBean mBeanServerFactoryBean = new MBeanServerFactoryBean();
//		return mBeanServerFactoryBean.getObject();
//	}
//
//	@Bean
//	public AnnotationJmxAttributeSource jmxAttributeSource() {
//		AnnotationJmxAttributeSource annotationJmxAttributeSource = new AnnotationJmxAttributeSource();
//		return annotationJmxAttributeSource;
//	}
//
//	@Bean
//	public MetadataMBeanInfoAssembler assembler() {
//		MetadataMBeanInfoAssembler metadataMBeanInfoAssembler = new MetadataMBeanInfoAssembler();
//		metadataMBeanInfoAssembler.setAttributeSource(jmxAttributeSource());
//		return metadataMBeanInfoAssembler;
//	}

//	@ManagedOperation(description = "Stops the Apache Camel Context")
//	@ManagedOperationParameter(name = "route", description = "i")
//	@Bean
//	@DependsOn(value = {"context"})
	public void stop(String route) throws Exception {
		camelContext.stopRoute(route);
//		return camelContext;
	}

	@Override
	@Bean
	public RouteBuilder route() {
		RouteBuilder deliveryNotificationRouter = new NotificationRouter();
		return deliveryNotificationRouter;
	}

	@Bean
	public ContentEnricherProcessor contentEnricherProcessor() throws Exception {
		ContentEnricherProcessor contentEnricherProcessor = new ContentEnricherProcessor(statusDAO);
		return contentEnricherProcessor;
	}

	@Bean
	public AccountProcessor accountProcessor() throws Exception {
		AccountProcessor accountProcessor = new AccountProcessor(statusDAO);
		return accountProcessor;
	}

	@Bean
	public SpringTransactionPolicy defaultTransactionPolicy() throws Exception {
		SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
		springTransactionPolicy.setTransactionManager(defaultTransactionManagerConfig.transactionManager());
		return springTransactionPolicy;
	}
}