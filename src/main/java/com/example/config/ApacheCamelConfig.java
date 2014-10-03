package com.example.config;

import java.util.Properties;

import javax.management.MBeanServer;

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
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.processor.AccountProcessor;
import com.example.processor.ContentEnricherProcessor;
import com.example.router.NotificationRouter;
import com.example.transactionmanager.DefaultTransactionManagerConfig;

@Configuration
@ManagedResource(objectName = "bean:name=apacheCamelConfig")
@EnableMBeanExport(server = "mBeanServer")
public class ApacheCamelConfig extends SingleRouteCamelConfiguration implements InitializingBean {

	private static CamelContext camelContext;
	@Autowired
	private DefaultTransactionManagerConfig defaultTransactionManagerConfig;
	private Properties properties;
	
	public ApacheCamelConfig() throws Exception {
 		properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("camel.properties"));
	}
	
//	@ManagedOperation
	@Bean(name = "context")
	protected CamelContext createCamelContext() throws Exception {
		camelContext = new SpringCamelContext(getApplicationContext());
		camelContext.setAutoStartup(false);
//		return camelContext = new SpringCamelContext(getApplicationContext());
		return camelContext;
	}
	
	@ManagedOperation
	public void start() throws Exception {
		camelContext.start();
	}
	
	protected void setupCamelContext(CamelContext camelContext) throws Exception {
		camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent(properties.getProperty("activemq.location")));
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}
	
	@Bean
	public MBeanServer mBeanServer() {
		MBeanServerFactoryBean mBeanServerFactoryBean = new MBeanServerFactoryBean();
		return mBeanServerFactoryBean.getObject();
	}
	
	@Bean
	public AnnotationJmxAttributeSource jmxAttributeSource() {
		AnnotationJmxAttributeSource annotationJmxAttributeSource = new AnnotationJmxAttributeSource();
		return annotationJmxAttributeSource;
	}
	
	@Bean
	public MetadataMBeanInfoAssembler assembler() {
		MetadataMBeanInfoAssembler metadataMBeanInfoAssembler = new MetadataMBeanInfoAssembler();
		metadataMBeanInfoAssembler.setAttributeSource(jmxAttributeSource());
		return metadataMBeanInfoAssembler;
	}
	
	@ManagedOperation(description = "Stops the Apache Camel Context")
	@ManagedOperationParameter(name = "route", description = "i")
//	@Bean
//	@DependsOn(value = {"context"})
	public void stop(String route) throws Exception {
		camelContext.stopRoute(route);
//		return camelContext;
	}
	
	@Bean
	public RouteBuilder route() {
		RouteBuilder deliveryNotificationRouter = new NotificationRouter();
		return deliveryNotificationRouter;
	}
	
	@Bean
	public ContentEnricherProcessor contentEnricherProcessor() throws Exception {
		ContentEnricherProcessor contentEnricherProcessor = new ContentEnricherProcessor();
		return contentEnricherProcessor;
	}
	
	@Bean
	public AccountProcessor accountProcessor() throws Exception {
		AccountProcessor accountProcessor = new AccountProcessor();
		return accountProcessor;
	}
	
	@Bean
	public SpringTransactionPolicy defaultTransactionPolicy() throws Exception {
		SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
		springTransactionPolicy.setTransactionManager((PlatformTransactionManager) defaultTransactionManagerConfig.transactionManager());
		return springTransactionPolicy;
	}
}