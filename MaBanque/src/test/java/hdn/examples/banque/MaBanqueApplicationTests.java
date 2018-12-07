package hdn.examples.banque;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaBanqueApplicationTests {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IGestionBanque service;

	@Value("${nom_DB}")
	private String databaseName;
	
	
	@Test
	public void contextLoads() {
	
		
	}

	@Test
	@Ignore("TESTED")
	public void test0_JdbcConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mabanque", "root", "admin");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("show tables from "+databaseName);
			rs.close();
			assertTrue("connection a la base OK via DriverManager", true);
		} catch (Exception exception) {
			fail("connection a la base KO via DriverManager");
		}
	}
	
	public void test1_Service() {
		Client c1 = clientRepository.save(new Client("Umberto", "bertik@sfr.fr"));
		Client c2 = clientRepository.save(new Client("Olga", "olga@sfr.fr"));
		
		Compte cpt1 = compteRepository.save(new CompteCourant("CC1", new Date(), 1000d, c1));
		Compte cpt2 = compteRepository.save(new CompteEpargne("CE1", new Date(), 1000d, c1));
		
		// appel direct du DAO
		operationRepository.save(new Retrait(new Date(), 100, cpt1));
		operationRepository.save(new Retrait(new Date(), 150, cpt1));
		operationRepository.save(new Versement(new Date(), 30, cpt1));
		operationRepository.save(new Retrait(new Date(), 700, cpt1));
		operationRepository.save(new Versement(new Date(), 1504, cpt1));
		
		operationRepository.save(new Retrait(new Date(), 1500, cpt2));
		operationRepository.save(new Retrait(new Date(), 80, cpt2));
		operationRepository.save(new Versement(new Date(), 3545, cpt2));
		operationRepository.save(new Retrait(new Date(), 10, cpt2));
		operationRepository.save(new Versement(new Date(), 150, cpt2));
		
		service.verser("CC1", 5000);
		service.virement("CC1", "CE1", 2500);
	}


	
}



