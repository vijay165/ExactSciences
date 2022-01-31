package com.title.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "avosEntityManagerFactory", transactionManagerRef = "avosTransactionManager", basePackages = {
		"com.title.avos.repository" })
public class AvosDBConfig {

	@Bean(name = "avosDataSource")
	@ConfigurationProperties(prefix = "spring.avos.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "avosEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("avosDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		//properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.title.model.avos").persistenceUnit("Avos").build();
	}

	@Bean(name = "avosTransactionManager")
	public PlatformTransactionManager bookTransactionManager(
			@Qualifier("avosEntityManagerFactory") EntityManagerFactory bookEntityManagerFactory) {
		return new JpaTransactionManager(bookEntityManagerFactory);
	}
}
