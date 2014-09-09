package com.example.router;

import java.util.Properties;
import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.context.ApplicationContext;
import com.example.entities.xml.Entity;
import com.example.processor.ContentEnricherProcessor;

/**
 * This class represents a NotificationRouter. This allows data to be routed to different processors.
 * @author Rajiv Jain
 */
public class NotificationRouter extends SpringRouteBuilder {

	/**
	 * This method configures the routes.
	 * @throws Exception when an Exception occurs.
	 */
	public void configure() throws Exception {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream(new String("camel.properties")));
		ApplicationContext applicationContext = getApplicationContext();
		from(properties.getProperty("source")).unmarshal().jaxb("com.example.entities.xml").convertBodyTo(Entity.class)
			.multicast()
			.to("direct:x")
			.end();
		from("direct:x").process((ContentEnricherProcessor) applicationContext.getBean("contentEnricherProcessor"))
						.to(ExchangePattern.InOnly, properties.getProperty("activemq.destination"));
	}
}