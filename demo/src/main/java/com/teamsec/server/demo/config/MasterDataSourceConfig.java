/**
 * 
 */
package com.teamsec.server.demo.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author admin
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMaster", // 配置连接工厂 entityManagerFactory
		transactionManagerRef = "transactionManagerMaster", // 配置 事物管理器 transactionManager
		basePackages = { "com.teamsec.server.demo.repository.student" })
public class MasterDataSourceConfig {
	@Autowired
	@Qualifier("master")
	private DataSource dataSourceMaster;
	@Autowired
	private JpaProperties jpaProperties;

	@Bean("entityManagerMaster")
	@Primary
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryBean(builder).getObject().createEntityManager();
	}

	@Bean("entityManagerFactoryMaster")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSourceMaster).properties(getVendorProperties())
				.packages("com.teamsec.server.demo")
				// 持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
				.persistenceUnit("masterPersistenceUnit").build();

	}

	private Map getVendorProperties() {
		HibernateSettings hibernateSettings = new HibernateSettings();
		// hibernateSettings.ddlAuto(ddlAuto);
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}

	@Bean("transactionManagerMaster")
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
	}
}
