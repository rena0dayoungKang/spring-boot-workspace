package com.kosta.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.entitiy.Member;
import com.kosta.board.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void join(MemberDto memberDto) throws Exception {
		memberRepository.save(memberDto.toEntity());
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member member = memberRepository.findById(id).orElseThrow(() -> new Exception("아이디 오류"));
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		return memberRepository.findById(id).isPresent();
	}

}
