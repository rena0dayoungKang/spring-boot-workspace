package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
