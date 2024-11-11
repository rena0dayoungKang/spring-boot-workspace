package com.kosta.board.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private MemberRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("------------------");
		System.out.println(username);
		Optional<Member> ouser = userRepository.findById(username);
		if(ouser.isPresent()) return new PrincipalDetails(ouser.get());
		return null;
	}

}
