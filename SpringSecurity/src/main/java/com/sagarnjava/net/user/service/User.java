package com.sagarnjava.net.user.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String passWord;
	
	private int active;
	
	private String roles;
	
	private String permissions;

	public User(String userName, String passWord, int active ,String roles ,String permissions) {
		// TODO Auto-generated constructor stub
	   this.userName=userName;
	   this.passWord=passWord;
	   this.active=active;
	   this.roles=roles;
	   this.permissions=permissions;
	   
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<String> getRoles() {
		return Arrays.asList(roles.split(","));
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return Arrays.asList(permissions.split(","));
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	
	
}
