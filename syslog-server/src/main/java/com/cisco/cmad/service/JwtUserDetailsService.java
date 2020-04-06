package com.cisco.cmad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cisco.cmad.dao.UserRepository;
import com.cisco.cmad.dto.UserInfo;
import com.cisco.cmad.model.User;

@Component
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		UserInfo userInfo=new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(user.getPassword());
		userInfo.setEnabled(true);
		userInfo.setCredentialsNonExpired(true);
		userInfo.setAccountNonLocked(true);
		userInfo.setAccountNonExpired(true);
		return userInfo;
	}

}
