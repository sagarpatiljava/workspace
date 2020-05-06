package com.sagarnjava.net;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringWebserviceWarApplication extends SpringBootServletInitializer {

	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(SpringWebserviceWarApplication.class);
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebserviceWarApplication.class, args);
	}
	
	

}
