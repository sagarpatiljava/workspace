package com.sagarnjava.net.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	
	public UserPrinciple(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//Extraacting permissions
		List<GrantedAuthority> authorities = new ArrayList<>();
		this.user.getPermissions().forEach(p->{
			GrantedAuthority ga=new SimpleGrantedAuthority(p);
			authorities.add(ga);
		});
		
		//Extracting roles list
		
		this.user.getRoles().forEach(p->{
			GrantedAuthority ga=new SimpleGrantedAuthority("ROLE_"+p);
			authorities.add(ga);
		});
		
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
