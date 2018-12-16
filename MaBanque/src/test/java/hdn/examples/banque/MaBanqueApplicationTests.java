package hdn.examples.banque;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
import hdn.examples.users.entities.Role;
import hdn.examples.users.entities.User;


/**
 * @author Administrator Auteur HDN Crée le Dec 16, 2018
 *
 *         Cette classe permet de ...
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:" + PropertyNames.APPLICATION_PROPERTIES_FILE_TEST)

public class MaBanqueApplicationTests {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private IGestionBanque service;

	@Value("${datasource1.name}")
	private String databaseName;

	@Autowired
	@Qualifier("entityManagerFactory2")
	LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean;

	@Autowired
	@Qualifier("transactionManager2")
	JpaTransactionManager jpaTrxManager;

	@Test
	@Ignore("TESTED OK")
	public void test0_JdbcConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mabanque", "root", "admin");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("show tables from " + databaseName);
			rs.close();
			assertTrue("connection a la base OK via DriverManager", true);
		} catch (Exception exception) {
			fail("connection a la base KO via DriverManager");
		}
	}

	@Test
	@Ignore("TESTED OK")
	public void test1_Service() {

		System.out.println("test1_Service");
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
	}

	@Test
	@Ignore("TESTED OK")
	public void test2_lireDataSourceUsers() {
		System.out.println("hello");

		try {

			// via un entityManager
			EntityManagerFactory emf = jpaTrxManager.getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			User userAdmin = em.find(User.class, "admin");

			assertNotNull(userAdmin);

			// via un criteraiBuilder
			CriteriaBuilder builder = jpaTrxManager.getEntityManagerFactory().getCriteriaBuilder();
			CriteriaQuery<Role> query = builder.createQuery(Role.class);
			Root<Role> c = query.from(Role.class);
			query = query.select(c).where(builder.equal(c.get("roleName"), "administrator"));
			TypedQuery<Role> typedQuery = em.createQuery(query);
			Role adminRole = typedQuery.getSingleResult();

			assertNotNull(adminRole);

		} catch (Exception e) {
			System.out.println("Exception avec le getEntityManagerFactory() ou la CriteriaQuery");

		}
	}
}
