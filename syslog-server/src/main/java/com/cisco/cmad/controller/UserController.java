package com.cisco.cmad.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cmad.dao.UserRepository;
import com.cisco.cmad.exception.HttpErrorException;
import com.cisco.cmad.model.User;

@RestController
@CrossOrigin
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createUser() {
		User userFromDb=userRepository.findByUsername("admin");
		if(userFromDb==null) {
			User user=new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin123"));
			user.setAuthLevel(1);
			userRepository.save(user);
			logger.info("Admin user created");
		}else {
			logger.info("Admin user already exists hence not creating");
		}
		
	}
	
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user) {
		if(user.getAuthLevel()==1) {
			throw new HttpErrorException("Not allowed to create a user with Auth level 1",10);
		}
		User userFromDb=userRepository.findByUsername(user.getUsername());
		if(userFromDb==null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		}else {
			throw new HttpErrorException(String.format("User with username %s already exists",user.getUsername()),11);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
}
