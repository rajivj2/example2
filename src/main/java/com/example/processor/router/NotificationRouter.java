package com.example.processor.router;

import java.util.Properties;
import javax.inject.Inject;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.context.ApplicationContext;
import com.example.entities.xml.Entity;
import com.example.processor.AccountProcessor;
import com.example.processor.ContentEnricherProcessor;

public class NotificationRouter extends SpringRouteBuilder {

	@Inject
	private ContentEnricherProcessor contentEnricherProcessor;
	@Inject
	private AccountProcessor accountProcessor;

	/**
	 * This method configures the routes.
	 * @throws Exception when an Exception occurs.
	 */
	@Override
	public void configure() throws Exception {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream(new String("camel.properties")));
		ApplicationContext applicationContext = getApplicationContext();
		from(properties.getProperty("source")).transacted().unmarshal().jaxb("com.example.entities.xml").convertBodyTo(Entity.class)
			.to("direct:x")
			.end();
		from("direct:x").transacted()
						.process(contentEnricherProcessor)
						.process(accountProcessor)
						.to(properties.getProperty("activemq.destination"));
	}
}