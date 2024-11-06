package com.kosta.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void join(MemberDto memberDto, MultipartFile profile) throws Exception {
		if (memberRepository.findById(memberDto.getId()).isPresent()) {
			throw new Exception("아이디 중복 오류");
		}
		Member member = memberDto.toEntity();
		if (profile != null) {
			member.setProfileImage(profile.getBytes());
		}
		memberRepository.save(member);

	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member member = memberRepository.findById(id).orElseThrow(() -> new Exception("아이디 오류"));
		if (!member.getPassword().equals(password))
			throw new Exception("비밀번호 오류");
		return member.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		return memberRepository.findById(id).isPresent();
	}

}
