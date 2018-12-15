package hdn.examples.users.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import hdn.examples.banque.conf.PropertyNames;


@Configuration
@PropertySource(PropertyNames.APPLICATION_PROPERTIES_FILE)
@EnableJpaRepositories(basePackages = {
		"hdn.examples.users..." }, entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager2")
@EnableTransactionManagement
public class UserRoleDataBaseConf {

	@Autowired
	private Properties hibernateProperties;

	@Bean(name = "transactionManager2")
	public PlatformTransactionManager transactionManager2() {
		return new JpaTransactionManager(entityManagerFactory2().getObject());
	}

	@Bean(name = "entityManagerFactory2")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory2() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		DataSource db = dataSourceUserRole();
		factoryBean.setDataSource(db);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setJpaProperties(hibernateProperties);
		factoryBean.setPackagesToScan("hdn.examples.users...");
		factoryBean.setPersistenceUnitName("users");

		return factoryBean;
	}
	
	@Bean(name = PropertyNames.DATABASE_USERS_PROPERTIES_PREFIX)
	@ConfigurationProperties(prefix = "datasource2")
	public DataSource dataSourceUserRole() {
		return DataSourceBuilder.create().build();
	}	


}