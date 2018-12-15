package hdn.examples.banque.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("appli")
@Component
public class BanqueProps {
	
	String nom;
	
	List<String> datasources;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<String> datasources) {
		this.datasources = datasources;
	}
	
	

}
