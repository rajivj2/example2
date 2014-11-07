package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

@Configuration
public class DefaultDataSourceFactoryConfig {

	@Autowired
	DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	@Autowired
	private HibernateConfig hibernateConfig;

	public DefaultDataSourceFactoryConfig() {

	}

	@Bean
	public DataSource defaultDataSource() throws Exception {
		DataSource dataSource = null;
   		if(hibernateConfig.getDataSourceXAClass().contains("NonXA")) {
   			dataSource = new AtomikosNonXADataSourceBean();
   			((AtomikosNonXADataSourceBean)dataSource).setUrl(defaultJDBCDataSourceConfig.getJdbcUrlDefault());
   			((AtomikosNonXADataSourceBean)dataSource).setUser(defaultJDBCDataSourceConfig.getJdbcUsername());
   			((AtomikosNonXADataSourceBean)dataSource).setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
   			((AtomikosNonXADataSourceBean)dataSource).setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
   			((AtomikosNonXADataSourceBean)dataSource).setUniqueResourceName("exampleDefaultNonXA");
   			((AtomikosNonXADataSourceBean)dataSource).setMaxPoolSize(5);
   		}
		return dataSource;
	}
}