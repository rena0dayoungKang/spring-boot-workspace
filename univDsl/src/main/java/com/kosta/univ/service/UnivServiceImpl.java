package com.kosta.univ.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.UnivDslRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final UnivDslRepository univDslRepository;

	//1. 부서등록
	@Override
	public void saveDepartment(DepartmentDto deptDto) throws Exception {
		univDslRepository.insertDept(deptDto.toEntity());
	}

	//2. 학생등록
	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		univDslRepository.insertStudent(studDto.toEntity());
	}

	//3. 교수등록
	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		univDslRepository.insertProf(profDto.toEntity());
	}

	//4. 학생 이름으로 학생목록 조회
	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		return univDslRepository.selectStudentByName(studName)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}
	
	//5. 제1전공 번호로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptno1) throws Exception {
		return univDslRepository.selectStudListByDeptno1(deptno1)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//5. 제1전공 [이름]으로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept1ByDeptName(String dname1) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListWithDeptname1(dname1);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}
	
	//6. 제2전공 번호로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptno2) throws Exception {
		return univDslRepository.selectStudListByDeptno2(deptno2)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}
	
	//6. 제2전공 [이름]으로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String dname2) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListWithDeptname2(dname2);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname2(i.get(1, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}

	//7. 학년으로 학생목록 조회
	@Override
	public List<StudentDto> studentListInByGrade(Integer grade) throws Exception {
		return univDslRepository.selectStudListByGrade(grade)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//8. 담당교수가 없는 학생목록 조회
	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		return univDslRepository.selectStudListByNoProf()
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//9. 학번으로 학생조회
	@Override
	public StudentDto studentByStudNo(Integer studno) throws Exception {
		return univDslRepository.selectStudentByStudNo(studno).toDto();
	}

	//10. 주민번호로 학생조회
	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		return univDslRepository.selectStudByJumin(jumin).toDto();
	}
	
	//11. 교수번호로 담당 학생목록 조회
	@Override
	public List<StudentDto> studentListByProfNo(Integer pforNo) throws Exception {
		return univDslRepository.selectStudListByProfNo(pforNo)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//12. 교수번호로 교수 조회
	@Override
	public ProfessorDto professorByProfNo(Integer profno) throws Exception {
		return univDslRepository.selectProfByProfNo(profno).toDto();
	}

	//13. 교수이름으로 교수 목록 조회
	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
		return univDslRepository.selectProfListByProfname(profName)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}
	
	//14. 학과번호로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		return univDslRepository.selectProfListByDeptno(deptNo)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//15. 학과이름으로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		return univDslRepository.selectProfListByDeptname(deptName)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//16. 직급으로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		return univDslRepository.selectProfListByPosition(position)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}

	//17. 학과번호로 학과 조회
	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		return univDslRepository.selectDeptByDeptNo(deptNo).toDto();
	}

	//18. 학과이름으로 학과 조회
	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		return univDslRepository.selectDeptByDname(deptName).toDto();
	}

	//19. 학과(part)로 학과목록 조회
	@Override
	public List<DepartmentDto> departmentListByPart(Integer part) throws Exception {
		return univDslRepository.selectDeptByPart(part)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}
	
	//20. 위치하는 건물로 학과목록 조회
	@Override
	public List<DepartmentDto> departmentListByBuild(String build) throws Exception {
		return univDslRepository.selectDeptByBuild(build)
								.stream()
								.map(i -> i.toDto())
								.collect(Collectors.toList());
	}
	
}
