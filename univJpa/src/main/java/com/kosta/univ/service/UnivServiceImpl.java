package com.kosta.univ.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {

	private final DepartmentRepository departmentRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

	@Override
	public void saveDepartment(DepartmentDto deptDto) throws Exception {
		departmentRepository.save(deptDto.toEntity());
	}

	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		professorRepository.save(profDto.toEntity());
	}

	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		studentRepository.save(studDto.toEntity());
	}

	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		return studentRepository.findByName(studName).stream().map(i -> i.toDto()).collect(Collectors.toList());
		// studentRepository.findByName(studName) 는 엔티티로 받아오기때문에 dto 형식으로 바꿔준다
	}
	
	@Override
	public List<StudentDto> studentListInByGrade(Integer grade) throws Exception {
		return studentRepository.findByGrade(grade).stream().map(i -> i.toDto()).collect(Collectors.toList());
	}


	@Override
	public List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception {

		// 방식 1. 명명규칙에 따른 조회
//		return studentRepository.findByDepartment1_dname(deptName)
//								.stream()
//								.map(i -> i.toDto())
//								.collect(Collectors.toList());

		// 방식 2. 연관되는 다른 테이블의 레포를 통해 객체 생성 후, 객체에 있는 함수 사용하는 방법
		Department dept = departmentRepository.findByDname(deptName).orElseThrow(() -> new Exception("학과명 오류"));
		return dept.getStudList1().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {

		// 방식 1.
//		return studentRepository.findByDepartment1_deptno(deptNo)
//								.stream()
//								.map(i -> i.toDto())
//								.collect(Collectors.toList())

		// 방식 2.
		Department dept = departmentRepository.findById(deptNo).orElseThrow(() -> new Exception("학과번호 오류"));
		return dept.getStudList1().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception {
		Department dept = departmentRepository.findByDname(deptName).orElseThrow(() -> new Exception("학과이름 오류"));
		return dept.getStudList2().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		Department dept = departmentRepository.findById(deptNo).orElseThrow(() -> new Exception("학과번호 오류"));
		return dept.getStudList2().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		return studentRepository.findByProfessor_ProfnoIsNull().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public StudentDto studentByStudNo(Integer studno) throws Exception {
		return studentRepository.findById(studno).orElseThrow(() -> new Exception("학번 오류")).toDto();
	}

	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		return studentRepository.findByJumin(jumin).orElseThrow(() -> new Exception("주민번호 오류")).toDto();
	}

	@Override
	public List<StudentDto> studentListByProfNo(Integer pforNo) throws Exception {
		Professor prof = professorRepository.findById(pforNo).orElseThrow(() -> new Exception("교수번호 오류"));
		return prof.getStudentList().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public ProfessorDto professorByProfNo(Integer profno) throws Exception {
		return professorRepository.findById(profno).orElseThrow(() -> new Exception("교수번호 오류")).toDto();
	}

	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
		return professorRepository.findByName(profName).stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		Department dept = departmentRepository.findById(deptNo).orElseThrow(() -> new Exception("학과번호 오류"));
		return dept.getProfList().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		Department dept = departmentRepository.findByDname(deptName).orElseThrow(() -> new Exception("학과이름 오류"));
		return dept.getProfList().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		return professorRepository.findByPosition(position).stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		return departmentRepository.findById(deptNo).orElseThrow(() -> new Exception("학과번호 오류")).toDto();
	}

	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		return departmentRepository.findByDname(deptName).orElseThrow(() -> new Exception("학과명 오류")).toDto();
	}

	@Override
	public List<DepartmentDto> departmentListByPart(Integer part) throws Exception {
		return departmentRepository.findByPart(part).stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentDto> departmentListByBuild(String build) throws Exception {
		return departmentRepository.findByBuild(build).stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

}
