package hdn.examples.banque;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import hdn.examples.banque.conf.BanqueProps;
import hdn.examples.banque.conf.DataSourceProperties;
import hdn.examples.banque.conf.PropertyNames;
import hdn.examples.banque.dao.ClientRepository;
import hdn.examples.banque.dao.CompteRepository;
import hdn.examples.banque.dao.OperationRepository;
import hdn.examples.banque.entites.Client;
import hdn.examples.banque.entites.Compte;
import hdn.examples.banque.entites.CompteCourant;
import hdn.examples.banque.entites.CompteEpargne;
import hdn.examples.banque.entites.Retrait;
import hdn.examples.banque.entites.Versement;
import hdn.examples.banque.metier.service.IGestionBanque;

@SpringBootApplication
@EnableConfigurationProperties(BanqueProps.class)
@ImportResource("classpath:"+PropertyNames.APPLICATION_CONTEXT_FILE)
@ComponentScan(basePackages = {"hdn..."})
public class MaBanqueApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(MaBanqueApplication.class);
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired 
	private OperationRepository operationRepository;
	
	@Autowired
	private IGestionBanque service;
	
	@Autowired
	DataSourceProperties dataSourceBanqueProperties; // exemple pour instancier un bean via un xml
	
	@Autowired
	BanqueProps banqueProps; // exemple pour instancier un bean via @ConfigurationProperties
	
	@Autowired 
	String welcomeMessage; // exemple pour instancier un bean instancié dans un xml
	
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	// appellé au lancement du contexte applicatif
	public void run(String... args) throws Exception {
		
		logger.info(welcomeMessage);
		logger.info("DATABASES : ");
		if (banqueProps != null) {
			for (String databaseName : banqueProps.getDatasources()) {
				logger.info(databaseName);
			}
		}
				
		Client c1 = clientRepository.save(new Client("Umberto", "bertik@sfr.fr"));
		Client c2 = clientRepository.save(new Client("Olga", "olga@sfr.fr"));
		
		Compte cpt1 = compteRepository.save(new CompteCourant("CC1", new Date(), 1000d, c1));
		Compte cpt2 = compteRepository.save(new CompteEpargne("CE1", new Date(), 3000d, c2));
		
		// appel direct du DAO
		operationRepository.save(new Retrait(new Date(), 100, cpt1));
		operationRepository.save(new Retrait(new Date(), 150, cpt1));
		operationRepository.save(new Versement(new Date(), 30, cpt1));
		operationRepository.save(new Retrait(new Date(), 700, cpt1));
		operationRepository.save(new Versement(new Date(), 1504, cpt1));
		
		//appel via le service
		//service.verser("CC1", 5000);
		//service.virement("CC1", "CE1", 2500);
		
		logger.info("Appli MaBanque lancée");
		
	}
}
