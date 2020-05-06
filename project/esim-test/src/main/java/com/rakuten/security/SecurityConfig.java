package com.rakuten.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override protected void configure(HttpSecurity http) throws Exception {
		  
		  http.authorizeRequests().antMatchers("/v1*")
		  .permitAll()
		  .and()
		  .httpBasic();
		  http.csrf().disable();
		  http.headers().frameOptions().disable();
		  http.headers().frameOptions().sameOrigin();
		  }
		
}
