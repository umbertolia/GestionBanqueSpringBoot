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
	
		  auth.jdbcAuthentication().dataSource(em.getDataSource()).
		  usersByUsernameQuery("select username as principal, password as credentials from user where username=?"
		  )
		  .authoritiesByUsernameQuery("select usename as principal, role as role from role_user where username=?"
		  ) .rolePrefix("ROLE_") .passwordEncoder(passwordEncoder());
		 

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
