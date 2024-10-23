package com.kosta.univ.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	Optional<Professor> findByNameAndDeptno(String name, Integer deptno);	//한학과에 교수명이 같은 사람 없음
}
