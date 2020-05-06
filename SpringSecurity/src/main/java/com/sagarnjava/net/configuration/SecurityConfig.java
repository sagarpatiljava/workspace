package com.sagarnjava.net.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sagarnjava.net.user.service.UserPrincipleService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        private UserPrincipleService userPrincipleService;

        
	  
	  public SecurityConfig(com.sagarnjava.net.user.service.UserPrincipleService userPrincipleService) {
			super();
			this.userPrincipleService = userPrincipleService;
		}


	@Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests().antMatchers("/security*")
	  .hasRole("ADMIN")
	  .and()
	  .httpBasic();
	  http.csrf().disable();
	  http.headers().frameOptions().disable();
	  http.headers().frameOptions().sameOrigin();
	  }
	  
	 
	/*
	 * @Override protected UserDetailsService userDetailsService() { // TODO
	 * Auto-generated method stub List<UserDetails> userDetails = new ArrayList<>();
	 * userDetails.add(User.username()) return super.userDetailsService(); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * auth.inMemoryAuthentication().withUser("sagar").password("sagar").roles(
		 * "user").and().withUser("test") .password("test").roles("admin");
		 */
		
            auth.authenticationProvider(getAuthenticationProvide());
		
		
	}
	

	
	@Bean
	DaoAuthenticationProvider getAuthenticationProvide() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(getPasswordEncoder());
		authProvider.setUserDetailsService(this.userPrincipleService);
		return authProvider;
	}

	
	  @Bean public PasswordEncoder getPasswordEncoder() { 
		  return new BCryptPasswordEncoder();
	  //NoOpPasswordEncoder.getInstance(); 
		  }
	 
}
