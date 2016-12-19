package hu.bme.msc.onlab.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "hu.bme.msc.onlab.dao.repository" })
public class JpaContextConfiguration {
	private static final String DRIVER_NAME_KEY = "hibernate.connection.driver_class";
	private static final String URL_KEY = "hibernate.connection.url";
	private static final String USERNAME_KEY = "hibernate.connection.username";
	private static final String P_KEY = "hibernate.connection.password";

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(env.getProperty(URL_KEY));
		basicDataSource.setUsername(env.getProperty(USERNAME_KEY));
		basicDataSource.setPassword(env.getProperty(P_KEY));
		basicDataSource.setDriverClassName(env.getProperty(DRIVER_NAME_KEY));
		// Pooling
		basicDataSource.setInitialSize(8);
		basicDataSource.setMaxIdle(8);

		return basicDataSource;
	}

	@Bean(name = "jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPackagesToScan("hu.bme.msc.onlab.model.sql");
		
		return localContainerEntityManagerFactoryBean;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

		return jpaTransactionManager;
	}
}
