package com.example.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class EntityManagerFactoryConfig {

	@Autowired
	private HibernateConfig hibernateConfig;
	@Autowired
	private DataSourceConfig dataSourceConfig;
	
	public EntityManagerFactoryConfig() {
		 
	}
	
	private Properties getDefaultJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.show_sql", hibernateConfig.getHibernateShowSQL());
		jpaProperties.setProperty("hibernate.dialect", hibernateConfig.getHibernateDialect());
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", hibernateConfig.getHibernateHbm2DDL());
		jpaProperties.setProperty("javax.persistence.jdbc.driver", dataSourceConfig.getJdbcDriverClassName());
		jpaProperties.setProperty("javax.persistence.jdbc.user", dataSourceConfig.getJdbcUsername());
		jpaProperties.setProperty("javax.persistence.jdbc.password", dataSourceConfig.getJdbcPassword());
		return jpaProperties;
	}
	
	@Bean
	@DependsOn(value = {"statusDAO"})
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSourceConfig.dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateConfig.jpaVendorAdapter());
		Properties jpaProperties = getDefaultJpaProperties();
		jpaProperties.setProperty("javax.persistence.jdbc.url", dataSourceConfig.getJdbcUrlDefault());
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("defaultEntityManager");
		return localContainerEntityManagerFactoryBean;
	}
}