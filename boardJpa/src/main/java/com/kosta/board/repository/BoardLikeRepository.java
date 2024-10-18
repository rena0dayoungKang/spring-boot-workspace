package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.BoardLike;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Integer> {

}
