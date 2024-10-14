package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	public MemberServiceImpl(MemberDao memberDAO) {
		this.memberDao = memberDAO;
	}

   @Override
   public void join(Member member) throws Exception {
	  //MemberDAO memberdao = new MemberDAOImpl(); 
	  //-> servlet-context.xml에서 memberService로 bean 생성 해두었기 때문에 없어도 됨.
      Member smember=memberDao.selectMember(member.getId());
      
      if(smember == null) {
    	  memberDao.insertMember(member);
      }else {
         throw new Exception("계정 중복");
      }

   }

   @Override
   public Member login(String id, String password) throws Exception {
      Member lmember = memberDao.selectMember(id); 
      
      if(lmember == null) throw new Exception("아이디 오류");
      if(!password.equals(lmember.getPassword())) throw new Exception("패스워드 오류"); 
      return lmember;
      
   }
   
   @Override
   public boolean checkDoubleId(String id) throws Exception {
      Member member = memberDao.selectMember(id);
      if(member == null) {
    	  return false;
      } 
      return true;
   }

}
