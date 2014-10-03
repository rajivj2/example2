package com.example.entitymanager;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.example.datasource.AccountDataSourceFactoryConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;

@Configuration
public class AccountEntityManagerFactoryConfig {

	@Autowired
	@Qualifier(value = "accountDataSource")
	DataSource accountDataSource;
	@Autowired
	private HibernateJpaVendorAdapter hibernateJpaVendorAdapter;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	@Autowired
	AccountDataSourceFactoryConfig accountDataSourceFactoryConfig;
	
	public AccountEntityManagerFactoryConfig() {
		 
	}
	
	@Bean
//	@DependsOn(value = {"liquibaseAccount"})
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryAccount() throws Exception {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(accountDataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		Properties jpaProperties = defaultJDBCDataSourceConfig.getDefaultJpaProperties();
		jpaProperties.setProperty("javax.persistence.jdbc.url", accountDataSourceFactoryConfig.getJdbcUrlAccount());
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("accountEntityManager");
		return localContainerEntityManagerFactoryBean;
	}
}