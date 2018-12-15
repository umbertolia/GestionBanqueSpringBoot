package hdn.examples.banque.conf;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class DataSourceProperties {
	
	
	private String name; 
	
	private String url;
	
	private String user;
	
	private String password;
	
	private String driverClassName;
	
	private Properties hibernateProperties;

	public DataSourceProperties(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public Properties getHibernateProperties() {
		return hibernateProperties;
	}

	public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

}
