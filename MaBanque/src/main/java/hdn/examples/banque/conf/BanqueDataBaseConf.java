package hdn.examples.banque.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource(PropertyNames.APPLICATION_PROPERTIES_FILE)
@EnableJpaRepositories(basePackages = {
		"hdn.examples.banque..." }, entityManagerFactoryRef = "entityManagerFactory1", transactionManagerRef = "transactionManager1")
@EnableTransactionManagement
public class BanqueDataBaseConf {

	@Autowired
	private Properties hibernateProperties;

	@Bean(name = "transactionManager1")
	@Primary
	public PlatformTransactionManager transactionManager1() {
		return new JpaTransactionManager(entityManagerFactory1().getObject());
	}

	@Bean(name = "entityManagerFactory1")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSourceBanque());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setJpaProperties(hibernateProperties);
		factoryBean.setPackagesToScan("hdn.examples.banque...");
		factoryBean.setPersistenceUnitName("banque");

		return factoryBean;
	}

	@Primary
	@Bean(name = PropertyNames.DATABASE_BANQUE_PROPERTIES_PREFIX)
	@ConfigurationProperties(prefix = "datasource1")
	public DataSource dataSourceBanque() {
		return DataSourceBuilder.create().build();
	}


}