package hdn.examples.banque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hdn.examples.banque.conf.BanqueProps;
import hdn.examples.banque.web.controller.AccesRefuseHandler;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(BanqueProps.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccesRefuseHandler accessDeniedHandler;

	@Autowired
	@Qualifier("entityManagerFactory2")
	LocalContainerEntityManagerFactoryBean em;
	
	@Autowired
	BanqueProps banqueProps; // exemple pour instancier un bean via @ConfigurationProperties
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	// Authentification avec users et roles statiques	
	//auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("administrator", "user");
	//auth.inMemoryAuthentication().withUser("toto").password(passwordEncoder().encode("toto")).roles("user");
		
	// Authentification avec users et roles en DB

	auth.jdbcAuthentication().dataSource(em.getDataSource())
		  .usersByUsernameQuery(banqueProps.getQueryUser())
		  .authoritiesByUsernameQuery(banqueProps.getQueryRole())
		  .rolePrefix("ROLE_")
		  .passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/operations/**").permitAll()
				.antMatchers("/comptes/**").hasRole(banqueProps.getRoleAdmin())
				.antMatchers("/clients/**").hasRole(banqueProps.getRoleAdmin())
				.antMatchers("/consultercompte/**").hasRole(banqueProps.getRoleUser())
				.antMatchers("/operationcompte/**").hasRole(banqueProps.getRoleAdmin())
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll()
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
