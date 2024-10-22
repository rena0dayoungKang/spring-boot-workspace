package com.kosta.univ.service;

import java.util.List;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;

public interface UnivService {
	//1. 부서등록
	void saveDepartment(DepartmentDto deptDto) throws Exception;
	//2. 학생등록
	void saveStudent(StudentDto studDto) throws Exception;
	//3. 교수등록
	void saveProfessor(ProfessorDto profDto) throws Exception;
	
	//4. 학생 이름으로 학생목록 조회
	List<StudentDto> studentListByName(String studName) throws Exception;
	//5. 제1전공으로 학생목록 조회
	List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception;
	List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception;
	//6. 제2전공으로 학생목록 조회
	List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception;
	List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception;
	//7. 학년으로 학생목록 조회
	List<StudentDto> studentListInByGrade(Integer grade) throws Exception;
	//8. 담당교수가 없는 학생목록 조회
	List<StudentDto> studentListByNoProfessor() throws Exception;
	//9. 학번으로 학생조회
	StudentDto studentByStudNo(Integer studno) throws Exception;
	//10. 주민번호로 학생조회
	StudentDto studentByJumin(String jumin) throws Exception;
	
	//11. 교수번호로 담당 학생목록 조회
	List<StudentDto> studentListByProfNo(Integer pforNo) throws Exception;
	
	//12. 교수번호로 교수 조회
	ProfessorDto professorByProfNo(Integer profno) throws Exception;
	//13. 교수이름으로 교수 목록 조회
	List<ProfessorDto> professorListByProfName(String profName) throws Exception;
	
	//14. 학과번호로 교수목록 조회
	List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception;
	//15. 학과이름으로 교수목록 조회
	List<ProfessorDto> professorListByDeptName(String deptName) throws Exception;
	//16. 직급으로 교수목록 조회
	List<ProfessorDto> professorListByPosition(String position) throws Exception;
	
	//17. 학과번호로 학과 조회
	DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception;
	//18. 학과이름으로 학과 조회
	DepartmentDto departmentByDeptName(String deptName) throws Exception;
	//19. 학과(part)로 학과목록 조회
	List<DepartmentDto> departmentListByPart(Integer part) throws Exception;
	//20. 위치하는 건물로 학과목록 조회
	List<DepartmentDto> departmentListByBuild(String build) throws Exception;
	
}
