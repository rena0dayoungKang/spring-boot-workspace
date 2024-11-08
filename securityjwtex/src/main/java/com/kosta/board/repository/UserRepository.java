package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
