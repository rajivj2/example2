package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

@Configuration
public class AccountDataSourceFactoryConfig {

	@Value("${jdbc.url.account}")
	private String jdbcUrlAccount;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	@Autowired
	private HibernateConfig hibernateConfig;

	public AccountDataSourceFactoryConfig() {

	}

	public String getJdbcUrlAccount() {
		return jdbcUrlAccount;
	}

	@Bean
	public DataSource accountDataSource() throws Exception {
		DataSource dataSource = null;
		if(hibernateConfig.getDataSourceXAClass().contains("NonXA")) {
			dataSource = new AtomikosNonXADataSourceBean();
			((AtomikosNonXADataSourceBean)dataSource).setUrl(jdbcUrlAccount);
			((AtomikosNonXADataSourceBean)dataSource).setUser(defaultJDBCDataSourceConfig.getJdbcUsername());
			((AtomikosNonXADataSourceBean)dataSource).setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
			((AtomikosNonXADataSourceBean)dataSource).setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
			((AtomikosNonXADataSourceBean)dataSource).setUniqueResourceName("exampleAccountNonXA");
			((AtomikosNonXADataSourceBean)dataSource).setMaxPoolSize(5);
		}
		return dataSource;
	}
}