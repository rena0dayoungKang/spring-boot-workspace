package com.kosta.board.service;

import org.springframework.stereotype.Service;

import com.kosta.board.dao.MemberDao;
import com.kosta.board.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDao memberDao;

	@Override
	public void join(Member member) throws Exception {
		Member smember = memberDao.selectMember(member.getId());
		if (smember != null)
			throw new Exception("아이디 중복 오류");
		memberDao.insertMember(member);
	}

	@Override
	public Member login(String id, String password) throws Exception {
		//MemberDao memberdao = new MemberDaoImpl();
		Member member = memberDao.selectMember(id); 
		if (member == null)
			throw new Exception("아이디 오류");
		if (!password.equals(member.getPassword()))
			throw new Exception("비밀번호 오류");
		return member;
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		//MemberDao memberDao = new MemberDaoImpl();
		Member member = memberDao.selectMember(id);
		if (member == null)
			return false;
		return true;
	}
}
