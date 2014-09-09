package com.example.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	
	@Value("${jdbc.url}")
	private String jdbcUrlDefault;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	public DataSourceConfig() {
		
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

	@Bean
	@DependsOn(value = {"properties"})
	public DataSource dataSource() throws Exception {
   		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(jdbcUrlDefault);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setDriverClassName(jdbcDriverClassName);
		return dataSource;
	}
}