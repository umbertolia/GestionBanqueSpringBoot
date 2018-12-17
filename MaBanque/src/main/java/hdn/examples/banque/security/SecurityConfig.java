package hdn.examples.banque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity	
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
   private AccessDeniedHandler accessDeniedHandler;

	 @Autowired 
	 @Qualifier("entityManagerFactory2")
	 LocalContainerEntityManagerFactoryBean em;
	 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");
		//auth.inMemoryAuthentication().withUser("toto").password(passwordEncoder().encode("toto")).roles("USER");
		
		// users + roles recuperes depuis une DB
	
		  auth.jdbcAuthentication().dataSource(em.getDataSource())
		  .usersByUsernameQuery(getUserQuery())
		  .authoritiesByUsernameQuery("select distinct user_name as principal, role_name as role from user_avec_role where user_name=?"
		  ).passwordEncoder(passwordEncoder());
		 

	}
	
	
	 private String getUserQuery() {
       return "select username as principal, password as credentials, actif as active from user where username=?";
   }



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		 	.authorizeRequests()
		 		.antMatchers("/operations/**").permitAll()
		 		.antMatchers("/consultercompte/**").hasAnyRole("USER")
		 		.antMatchers("/operationcompte/**").hasAnyRole("ADMIN")
		 		.and()
		 		.formLogin().loginPage("/login").permitAll()
		 			.and()
		 		.logout().permitAll()
		 			.and()
		 		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}


	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
