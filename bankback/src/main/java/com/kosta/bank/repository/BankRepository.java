package com.kosta.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class BankRepository {
	//save나 findBy 는 jpa 레포지토리를 이용하고 그 외는 동적쿼리 dsl을 이용하려고 함
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;

	// 계좌개설
	public void insertAccount(Account account) {
		accountRepository.save(account);
	}
	
	// 계좌조회 (동적쿼리)
	public Account findAccountById(String id) {
		QAccount account = QAccount.account;
		return jpaQueryFactory.select(account) //전체 (*) 일 경우 테이블명 그대로 씀
					   		  .from(account)
					   		  .where(account.id.eq(id))
					   		  .fetchOne();	
	}
	
	// 입금, 출금 (동적쿼리)
	@Transactional 				//데이터를 바꾸는 애들은 @Transactional 어노테이션 필요 
	public void updateBalance(String id, Integer balance) {
		QAccount account = QAccount.account;
		jpaQueryFactory.update(account)
						.set(account.balance, balance)
						.where(account.id.eq(id))
						.execute();
	}
	
	// 전체계좌조회
	public List<Account> findAllAccount() {
		QAccount account = QAccount.account;
		return jpaQueryFactory.selectFrom(account).fetch();
	}
	
	// 계좌이체
	@Transactional
	public void transfer(String sid, String rid, Integer sbalance, Integer rbalance) {
//		QAccount sendAccount = QAccount.account;
//		QAccount recvAccount = QAccount.account; <!-- 이렇게 하면 오류남, 한 쿼리는 한개만 -->
		
		//각각을 new 해주어야 한다 
		QAccount sendAccount = new QAccount("account1");
		QAccount recvAccount = new QAccount("account2");
		
		jpaQueryFactory.update(sendAccount)
						.set(sendAccount.balance, sbalance)
						.where(sendAccount.id.eq(sid))
						.execute();
		
		jpaQueryFactory.update(recvAccount)
						.set(recvAccount.balance, rbalance)
						.where(recvAccount.id.eq(rid))
						.execute();
	}
	
	// 회원가입
	public void insertMember(Member member) throws Exception {
		Optional<Member> oMember = memberRepository.findById(member.getId());
		if(oMember.isPresent()) throw new Exception("중복 아이디 오류");
		memberRepository.save(member);
	}	
	
	// 회원조회(로그인)
	public Member findMemberById(String id) throws Exception {
		Member member = memberRepository.findById(id).orElseThrow(() -> new Exception("아이디 오류"));
		return member;
	}
	
	// 중복아이디조회
	public boolean existMemberId(String id) throws Exception {
		return memberRepository.findById(id).isPresent();		
	}
}
