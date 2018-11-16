package com.teamsec.server.demo.config;

import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(value = "com.teamsec.server.demo.repository")
public class JpaEntityManager {

	@Autowired
	private JpaProperties jpaProperties;

	@Resource(name = "routingDataSource")
	private DataSource routingDataSource;

	// @Primary
	@Bean(name = "entityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		// 不明白为什么这里获取不到 application.yml 里的配置
		Map<String, String> properties = jpaProperties.getProperties();
		// 要设置这个属性，实现 CamelCase -> UnderScore 的转换
		properties.put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

		return builder.dataSource(routingDataSource)// 关键：注入routingDataSource
				.properties(properties).packages("com.teamsec.**.entity").persistenceUnit("myPersistenceUnit").build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return this.entityManagerFactoryBean(builder).getObject();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder));
	}
}