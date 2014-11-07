package com.example.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateConfig {

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSQL;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2DDL;
	@Value("${dataSourceXAClass}")
	private String dataSourceXAClass;

	public HibernateConfig() {

	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
  		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);
		return hibernateJpaVendorAdapter;
	}

	/**
	 * This method gets the hibenate dialect.
	 * @return the hibernate dialect.
	 */
	public String getHibernateDialect() {
		return hibernateDialect;
	}

	/**
	 * This method gets the hibernate shoq sql.
	 * @return the hibernate show sql.
	 */
	public String getHibernateShowSQL() {
		return hibernateShowSQL;
	}

	/**
	 * This method gets the hibernate hbm2ddl.
	 * @return the hibernate hbm2ddl.
	 */
	public String getHibernateHbm2DDL() {
		return hibernateHbm2DDL;
	}

	public String getDataSourceXAClass() {
		return dataSourceXAClass;
	}
}