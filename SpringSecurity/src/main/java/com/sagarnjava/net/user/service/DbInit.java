package com.sagarnjava.net.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements ApplicationRunner{
	 private UserRepository userRepository;
	 private PasswordEncoder passwordEncoder;

	    @Autowired
	    public DbInit(UserRepository userRepository ,PasswordEncoder passwordEncoder) {
	       
	    	System.out.println("Database Initialised");
	    	this.userRepository = userRepository;
	        this.passwordEncoder=passwordEncoder;
	    }

	   
		@Override
		public void run(ApplicationArguments args) throws Exception {
			// TODO Auto-generated method stub
			 userRepository.save(new User("sagar", passwordEncoder.encode("sagar"), 1,"ADMIN,USER","ALL"));
			 userRepository.save(new User("test", passwordEncoder.encode("test"), 1,"ADMIN,USER","ALL"));
			 userRepository.save(new User("ashu", passwordEncoder.encode("ashu"), 1,"ADMIN,USER","ALL"));
			 
		}
}
