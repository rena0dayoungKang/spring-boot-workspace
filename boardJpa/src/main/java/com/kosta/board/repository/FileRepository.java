package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entitiy.BFile;

public interface FileRepository extends JpaRepository<BFile, Integer> {

}
