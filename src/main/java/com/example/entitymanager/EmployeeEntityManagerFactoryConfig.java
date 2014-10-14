package com.example.entitymanager;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.EmployeeDataSourceFactoryConfig;

@Configuration
public class EmployeeEntityManagerFactoryConfig {

	@Autowired
	@Qualifier(value = "employeeDataSource")
	DataSource employeeDataSource;
	@Autowired
	private HibernateJpaVendorAdapter hibernateJpaVendorAdapter;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	@Autowired
	EmployeeDataSourceFactoryConfig employeeDataSourceFactoryConfig;

	public EmployeeEntityManagerFactoryConfig() {

	}

	@Bean
//	@DependsOn(value = {"liquibaseEmployee"})
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryEmployee() throws Exception {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(employeeDataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		Properties jpaProperties = defaultJDBCDataSourceConfig.getDefaultJpaProperties();
		jpaProperties.setProperty("javax.persistence.jdbc.url", employeeDataSourceFactoryConfig.getJdbcUrlEmployee());
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("employeeEntityManager");
		return localContainerEntityManagerFactoryBean;
	}
}