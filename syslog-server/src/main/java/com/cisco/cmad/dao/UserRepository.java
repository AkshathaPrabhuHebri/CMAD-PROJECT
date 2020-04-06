package com.cisco.cmad.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cisco.cmad.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByUsername(String username);
}
