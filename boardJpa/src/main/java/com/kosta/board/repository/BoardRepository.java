package com.kosta.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//PageRequest를 통해 가져가는 애는 항상 return 타입이 page이다
	Page<Board> findBySubjectContains(String subject, PageRequest pageRequest); //제목으로 검색
	Page<Board> findByContentContains(String content, PageRequest pageRequest); //내용으로 검색
	Page<Board> findByMember_Nickname(String writer, PageRequest pageRequest); //작성자로 검색
	
}
