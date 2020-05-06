package com.sagarnjava.net.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipleService implements UserDetailsService {

 private UserRepository userRepository;
 
 	public UserPrincipleService(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepository.findByUserName(username);
		UserPrinciple userPrinciple = new UserPrinciple(user);
		return userPrinciple;
	}

}
