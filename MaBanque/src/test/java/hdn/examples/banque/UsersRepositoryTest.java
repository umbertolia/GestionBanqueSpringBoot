package hdn.examples.banque;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:banque.properties")
public class UsersRepositoryTest {
	
	@Autowired 
	String welcomeMessage; // exemple pour instancier un bean instancié dans un xml
	
 
	@Test
	public void contextLoads() {
		System.out.println("###############################################################");
		System.out.println("============================Testing============================");
		System.out.println("###############################################################");
	}
 
	
   @Test
	public void test0_JdbcConnection() {
		System.out.println("coucou");
	}
 
}