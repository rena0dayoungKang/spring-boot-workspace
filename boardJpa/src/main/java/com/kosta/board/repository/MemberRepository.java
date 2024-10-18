package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
