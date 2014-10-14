package com.example.entitymanager;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.HibernateConfig;

//@Configuration
public class DefaultEntityManagerFactoryConfig {

//	@Autowired
//	@Qualifier(value = "defaultDataSource")
//	DataSource defaultDataSource;
//	@Autowired
	HibernateJpaVendorAdapter hibernateJpaVendorAdapter;
	@Autowired
	private HibernateConfig hibernateConfig;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;

	public DefaultEntityManagerFactoryConfig() {

	}

//	@Bean
//	@DependsOn(value = {"liquibase"})
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//		localContainerEntityManagerFactoryBean.setDataSource(defaultDataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		Properties jpaProperties = defaultJDBCDataSourceConfig.getDefaultJpaProperties();
		jpaProperties.setProperty("javax.persistence.jdbc.url", defaultJDBCDataSourceConfig.getJdbcUrlDefault());
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("defaultEntityManager");
		return localContainerEntityManagerFactoryBean;
	}
}