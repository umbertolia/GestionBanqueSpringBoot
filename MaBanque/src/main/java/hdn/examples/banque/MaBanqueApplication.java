package hdn.examples.banque;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	// appellé au lancement du contexte applicatif
	public void run(String... args) throws Exception {
		
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
