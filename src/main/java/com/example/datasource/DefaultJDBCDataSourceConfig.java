package com.example.datasource;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultJDBCDataSourceConfig {
	
	@Autowired
	HibernateConfig hibernateConfig;
	@Value("${jdbc.url}")
	private String jdbcUrlDefault;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	public DefaultJDBCDataSourceConfig() {
		
	}
	
	public Properties getDefaultJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.show_sql", hibernateConfig.getHibernateShowSQL());
		jpaProperties.setProperty("hibernate.dialect", hibernateConfig.getHibernateDialect());
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", hibernateConfig.getHibernateHbm2DDL());
		jpaProperties.setProperty("javax.persistence.jdbc.driver", jdbcDriverClassName);
		jpaProperties.setProperty("javax.persisence.jdbc.user", jdbcUsername);
		jpaProperties.setProperty("javax.persistence.jdbc.password", jdbcPassword);
		return jpaProperties;
	}
	
	/**
	 * This method gets the jdbc default url.
	 * @return the jdbc default url.
	 */
	public String getJdbcUrlDefault() {
		return jdbcUrlDefault;
	}
	
	/**
	 * This method gets the jdbc username.
	 * @return the jdbc username.
	 */
	public String getJdbcUsername() {
		return jdbcUsername;
	}

	/**
	 * This method gets the jdbc password.
	 * @return the jdbc password.
	 */
	public String getJdbcPassword() {
		return jdbcPassword;
	}

	/**
	 * This method gets the jdbc driver class name.
	 * @return the jdbc driver class name.
	 */
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}
}