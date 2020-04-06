package com.cisco.cmad.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cisco.cmad.dto.UserInfo;

@Component
public class JwtUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo=new UserInfo();
		userInfo.setUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("blah");
		userInfo.setPassword(hashedPassword);
		userInfo.setEnabled(true);
		userInfo.setCredentialsNonExpired(true);
		userInfo.setAccountNonLocked(true);
		userInfo.setAccountNonExpired(true);
		return userInfo;
	}

}
