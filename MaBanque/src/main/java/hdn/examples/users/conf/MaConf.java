package hdn.examples.users.conf;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MaConf {
	
	String message;

	public MaConf(String message) {
		super();
		this.message = message;
		System.out.println("constructeur");
	}
	
	

}
