package com.kosta.sec.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

// security 설정에서 loginProcessingUrl("/loginProc")
// /loginProc 요청이 오면 자동으로 UserDetailsService의 타입으로 IoC되어있는 loadUserByUsername함수가 호출된다.
@Service
public class PricipalDetailsService implements UserDetailsService {
									// 시큐리티가 제공하는 UserDetailsService의 구현체만들어줌
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no username"));
		return new PrincipalDetails(user);
	}

}
