package com.sagarnjava.net;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringHttpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHttpsApplication.class, args);
	}

	@Bean
	 public ServletWebServerFactory servletContainer() {
	
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	
		@Override
		protected void postProcessContext(Context context) {
			// TODO Auto-generated method stub
			SecurityConstraint securityConstraint = new SecurityConstraint();
			securityConstraint.setUserConstraint("CONFIDENTIAL");
			SecurityCollection collection = new SecurityCollection();
			collection.addPattern("/security*");
			securityConstraint.addCollection(collection);
			context.addConstraint(securityConstraint);
			super.postProcessContext(context);
		}
		};
		return tomcat;	
			
		}
	}

