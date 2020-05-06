package com.sagarnjava.net.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
}
