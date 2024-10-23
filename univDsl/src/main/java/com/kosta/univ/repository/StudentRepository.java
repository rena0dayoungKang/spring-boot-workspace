package com.kosta.univ.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Optional<Student> findByJumin(String jumin);
	List<Student> findByName(String name);
}
