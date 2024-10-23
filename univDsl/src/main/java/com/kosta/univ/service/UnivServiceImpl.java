package com.kosta.univ.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.repository.UnivDslRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final UnivDslRepository univDslRepository;
	
	private final DepartmentRepository departmentRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

	//1. 부서등록
	@Override
	public void saveDepartment(DepartmentDto deptDto) throws Exception {
		if(departmentRepository.findByDname(deptDto.getDname()).isPresent()) {
			throw new Exception("등록된 학과입니다");
		}
		departmentRepository.save(deptDto.toEntity());
	}

	//2. 학생등록
	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		if(studentRepository.findById(studDto.getStudno()).isPresent()) {
			throw new Exception("등록된 학생입니다");
		}

		//학과번호 X, 학과명 O일 경우, 학과명으로 학과번호 조회 후 저장
		if(studDto.getDeptno1() == null && studDto.getDname1() != null) {
			studDto.setDeptno1(departmentRepository.findByDname(studDto.getDname1())
					.orElseThrow(() -> new Exception("학과명 오류")).getDeptno());
		}
		if(studDto.getDeptno2() == null && studDto.getDname2() != null) {
			studDto.setDeptno2(departmentRepository.findByDname(studDto.getDname2())
					.orElseThrow(() -> new Exception("학과명 오류")).getDeptno());
		}
		
		if(studDto.getProfno() == null && studDto.getProfname() != null) {
			studDto.setProfno(professorRepository.findByNameAndDeptno(studDto.getProfname(), studDto.getDeptno1())
					.orElseThrow(() -> new Exception("교수이름 오류")).getProfno());
		}
		
		studentRepository.save(studDto.toEntity());
	}

	//3. 교수등록
	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		if(professorRepository.findById(profDto.getProfno()).isPresent()) {
			throw new Exception("등록된 교수입니다");
		}
		
		//학과번호 X, 학과명 O일 경우, 학과명으로 학과번호 조회 후 저장
		if(profDto.getDeptno() == null && profDto.getDname() != null) {
			profDto.setDeptno(departmentRepository.findByDname(profDto.getDname())
					.orElseThrow(() -> new Exception("학과명 오류")).getDeptno());
		}
		
		professorRepository.save(profDto.toEntity());
	}

	//4. 학생 이름으로 학생목록 조회
	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		return univDslRepository.selectStudListByName(studName);
	}
	
	//5. 제1전공 번호로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptno1) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListByDeptno1(deptno1);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
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
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}
	
	//6. 제2전공 번호로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptno2) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListByDeptno2(deptno2);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}
	
	//6. 제2전공 [이름]으로 학생목록 조회
	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String dname2) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListWithDeptname2(dname2);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}

	//7. 학년으로 학생목록 조회
	@Override
	public List<StudentDto> studentListInByGrade(Integer grade) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListByGrade(grade);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}

	//8. 담당교수가 없는 학생목록 조회
	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListByNoProf();
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}

	//9. 학번으로 학생조회
	@Override
	public StudentDto studentByStudNo(Integer studno) throws Exception {
		Tuple tuple = univDslRepository.selectStudentByStudNo(studno);
		StudentDto studDto = tuple.get(0, Student.class).toDto();
		studDto.setDname1(tuple.get(1, String.class));
		studDto.setDname2(tuple.get(2, String.class));
		studDto.setProfname(tuple.get(3, String.class));
		return studDto;
	}

	//10. 주민번호로 학생조회
	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		Tuple tuple = univDslRepository.selectStudByJumin(jumin);
		StudentDto studDto = tuple.get(0, Student.class).toDto();
		studDto.setDname1(tuple.get(1, String.class));
		studDto.setDname2(tuple.get(2, String.class));
		studDto.setProfname(tuple.get(3, String.class));
		return studDto;
		
	}
	
	//11. 교수번호로 담당 학생목록 조회
	@Override
	public List<StudentDto> studentListByProfNo(Integer pforNo) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectStudListByProfNo(pforNo);
		return tupleList.stream()
						.map(i -> {
							StudentDto studentDto = i.get(0, Student.class).toDto();
							studentDto.setDname1(i.get(1, String.class));
							studentDto.setDname2(i.get(2, String.class));
							studentDto.setProfname(i.get(3, String.class));
							return studentDto;
						})
						.collect(Collectors.toList());
	}

	//12. 교수번호로 교수 조회
	@Override
	public ProfessorDto professorByProfNo(Integer profno) throws Exception {
		Tuple tuple = univDslRepository.selectProfByProfNo(profno);
		ProfessorDto professorDto = tuple.get(0, Professor.class).toDto();
		professorDto.setDname(tuple.get(1, String.class));
		return professorDto;
	}

	//13. 교수이름으로 교수 목록 조회 <!-- 이런 방법도 있음! --> 
	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
		return univDslRepository.selectProfListByProfname(profName);
	}
	
	//14. 학과번호로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectProfListByDeptno(deptNo);
		return tupleList.stream()
						.map(i -> {
							ProfessorDto professorDto = i.get(0, Professor.class).toDto();
							professorDto.setDname(i.get(1, String.class));
							return professorDto;
						})
						.collect(Collectors.toList());
	}

	//15. 학과이름으로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectProfListByDeptname(deptName);
		return tupleList.stream()
						.map(i -> {
							ProfessorDto professorDto = i.get(0, Professor.class).toDto();
							professorDto.setDname(i.get(1, String.class));
							return professorDto;
						})
						.collect(Collectors.toList());
	}

	//16. 직급으로 교수목록 조회
	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		List<Tuple> tupleList = univDslRepository.selectProfListByPosition(position);
		return tupleList.stream()
						.map(i -> {
							ProfessorDto professorDto = i.get(0, Professor.class).toDto();
							professorDto.setDname(i.get(1, String.class));
							return professorDto;
						})
						.collect(Collectors.toList());
	}

	//17. 학과번호로 학과 조회
	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		return departmentRepository.findById(deptNo)
								   .orElseThrow(() -> new Exception("학과번호 오류"))
								   .toDto();
		
	}

	//18. 학과이름으로 학과 조회
	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		return departmentRepository.findByDname(deptName)
								   .orElseThrow(() -> new Exception("학과 이름 오류"))
								   .toDto();
	}

	//19. 학과(part)로 학과목록 조회
	@Override
	public List<DepartmentDto> departmentListByPart(Integer part) throws Exception {
		return departmentRepository.findByPart(part)
								   .stream()
								   .map(i -> i.toDto())
								   .collect(Collectors.toList());
	}
	
	//20. 위치하는 건물로 학과목록 조회
	@Override
	public List<DepartmentDto> departmentListByBuild(String build) throws Exception {
		return departmentRepository.findByBuild(build)
								   .stream()
								   .map(i -> i.toDto())
								   .collect(Collectors.toList());
	}
	
}
