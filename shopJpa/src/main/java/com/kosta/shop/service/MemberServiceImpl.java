package com.kosta.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dto.MemberDto;
import com.kosta.shop.entity.Member;
import com.kosta.shop.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public MemberDto login(String userid, String passwd) throws Exception {
		Member member = memberRepository.findById(userid).orElseThrow(() -> new Exception("아이디 오류"));
		if (!member.getPasswd().equals(passwd)) throw new Exception("비밀번호 오류");
		return member.toDto();
	}

	@Override
	public void signUp(MemberDto memberDto) throws Exception {
		memberRepository.save(memberDto.toEntity());

	}

	@Override
	public boolean idCheck(String userid) throws Exception {
		return memberRepository.findById(userid).isPresent();
	}

}
