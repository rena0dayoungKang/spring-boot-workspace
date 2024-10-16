package com.kosta.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	
	@Override
	public void join(Member member) throws Exception {
		memberRepository.save(member);
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member member = memberRepository.findById(id).orElseThrow(() -> new Exception("아이디 오류"));
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member;
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) return true;
		return false;
	}

}
