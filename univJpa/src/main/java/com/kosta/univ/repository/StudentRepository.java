package com.kosta.univ.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.univ.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByName(String studName);
	List<Student> findByGrade(Integer grade);
	
	//방식 1. 레포지토리를 명명규칙에 따라 만들고 테이블 관계가 있는 애들끼리 조회 
	//findBy + 참조한 Entity명 + _ +참조한Entity의 컬럼명 + 기타 으로 작성하면 된다.
//	List<Student> findByDepartment1_deptno(Integer deptno);
//	List<Student> findByDepartment1_dname(String dname);
	
	List<Student> findByProfessor_ProfnoIsNull();
	Optional<Student> findByJumin(String jumin);
	
	
	
	
}
