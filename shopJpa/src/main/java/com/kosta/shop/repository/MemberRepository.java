package com.kosta.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.shop.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
