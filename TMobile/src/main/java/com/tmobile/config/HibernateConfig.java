package com.tmobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Bean
	public LocalEntityManagerFactoryBean createEntityManagerFactory() {
		LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
		factory.setPersistenceUnitName("tttprovider-pu");
		return factory;
	}
	
	@Bean 
	public JpaTransactionManager createJpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(createEntityManagerFactory().getObject());
		return transactionManager;
	}
}
