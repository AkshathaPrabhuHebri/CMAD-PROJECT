package com.cisco.cmad.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.cmad.dao.UserRepository;
import com.cisco.cmad.enums.Role;
import com.cisco.cmad.exception.HttpErrorException;
import com.cisco.cmad.model.User;

@RestController
@CrossOrigin
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
			user.getRoles().add(Role.ADMIN.toString());
			userRepository.save(user);
			logger.info("Admin user created");
		}else {
			logger.info("Admin user already exists hence not creating");
		}
		
	}
	
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user) {
		if(user.getRoles().contains(Role.ADMIN.toString())) {
			throw new HttpErrorException("Not allowed to create a user with Auth level 1",10);
		}
		User userFromDb=userRepository.findByUsername(user.getUsername());
		if(userFromDb==null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.getRoles().add(Role.USER.toString());
			userRepository.save(user);
		}else {
			throw new HttpErrorException(String.format("User with username %s already exists",user.getUsername()),11);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> get() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}
}
