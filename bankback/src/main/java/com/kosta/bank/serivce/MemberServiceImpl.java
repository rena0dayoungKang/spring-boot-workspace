package com.kosta.bank.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.BankRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private BankRepository bankRepository;
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		bankRepository.insertMember(memberDto.toEntity());
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member member = bankRepository.findMemberById(id);
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		return bankRepository.existMemberId(id);		
	}

}
