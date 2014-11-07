package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

@Configuration
public class EmployeeDataSourceFactoryConfig {

	@Value("${jdbc.url.employee}")
	private String jdbcUrlEmployee;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	@Autowired
	private HibernateConfig hibernateConfig;

	public EmployeeDataSourceFactoryConfig() {

	}

	public String getJdbcUrlEmployee() {
		return jdbcUrlEmployee;
	}

	@Bean
	public DataSource employeeDataSource() throws Exception {
		DataSource dataSource = null;
		if(hibernateConfig.getDataSourceXAClass().contains("NonXA")) {
			dataSource = new AtomikosNonXADataSourceBean();
			((AtomikosNonXADataSourceBean)dataSource).setUrl(jdbcUrlEmployee);
			((AtomikosNonXADataSourceBean)dataSource).setUser(defaultJDBCDataSourceConfig.getJdbcUsername());
			((AtomikosNonXADataSourceBean)dataSource).setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
			((AtomikosNonXADataSourceBean)dataSource).setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
			((AtomikosNonXADataSourceBean)dataSource).setUniqueResourceName("exampleEmployeeNonXA");
			((AtomikosNonXADataSourceBean)dataSource).setMaxPoolSize(5);
		}
		return dataSource;
	}
}