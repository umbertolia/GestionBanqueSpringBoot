package hdn.examples.banque;

import hdn.examples.banque.metier.service.IGestionBanque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(MaBanqueApplication.class);
	
	@Autowired
	private IGestionBanque service;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	// appellé au lancement du contexte applicatif
	public void run(String... args) throws Exception {
		
		logger.info("Appli MaBanque lancée");
		
	}
}
