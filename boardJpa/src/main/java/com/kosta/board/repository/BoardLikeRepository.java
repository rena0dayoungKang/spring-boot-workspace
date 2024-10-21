package com.kosta.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.BoardLike;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Integer> {
	Optional<BoardLike> findByMember_IdAndBoard_Num(String memId, Integer boardNum);
}
