package com.kosta.board.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.board.entity.User;
import com.kosta.board.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("username : "+username);
		User user = userRepository.findByUsername(username);
//		System.out.println(user);
		if(user != null) return new PrincipalDetails(user);
		return null;
	}

}
