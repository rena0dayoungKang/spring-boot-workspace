package com.kosta.univ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.service.UnivService;

@RestController
public class UnivController {
	
	@Autowired
	private UnivService univService;
	
	//1. 부서등록
	@PostMapping("/regDept")
	public ResponseEntity<String> registDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
			univService.saveDepartment(departmentDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	//2. 학생등록
	@PostMapping("/regStud")
	public ResponseEntity<String> registStudent(@RequestBody StudentDto studentDto) {
		try {
			univService.saveStudent(studentDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	//3. 교수등록
	@PostMapping("/regProf")
	public ResponseEntity<String> registProfessor(@RequestBody ProfessorDto professorDto) {
		try {
			univService.saveProfessor(professorDto);
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
		}
	}
	
	//4. 학생 이름으로 학생목록 조회
	@GetMapping("/studInfoByName")	//학생명으로 학생 조회
	public ResponseEntity<List<StudentDto>> studInfoByName(@RequestParam("name") String name) {
		try {
			List<StudentDto> studentList = univService.studentListByName(name);
			return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			//@Rest 에서는 BAD_REQUEST 하면 
			//참조 : Ajax에서는 BAD_REQUEST로 하면 에러로 빠지게 된다. 
		}
	}
	
	//5. 제1전공 번호로 학생목록 조회
	@GetMapping("/studInDeptNo")	//학과번호에 소속된 학생들 조회 (주전공)
	public ResponseEntity<List<StudentDto>> studInDeptNo(@RequestParam("deptno") Integer deptno) {
		try {
			List<StudentDto> studDtoList = univService.studentListInDept1ByDeptNo(deptno);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	//5. 제1전공 [이름]으로 학생목록 조회
	@GetMapping("/studInDeptName")	//학과명에 소속된 학생들 조회 (주전공)
	public ResponseEntity<List<StudentDto>> studInDeptName(@RequestParam("dname1") String dname1) {
		try {
			List<StudentDto> studDtoList = univService.studentListInDept1ByDeptName(dname1);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//6. 제2전공 번호로 학생목록 조회
	@GetMapping("/studInDeptNo2")	// 학과번호에 소속된 학생들 조회 (부전공)
	public ResponseEntity<List<StudentDto>> studenInDepartmentByDeptno2(@RequestParam("deptno") Integer deptno){
		try {
			List<StudentDto> studDtoList = univService.studentListInDept2ByDeptNo(deptno);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);	
		}
	}
	
	//6. 제2전공 [이름]으로 학생목록 조회
	@GetMapping("/studInDeptName2") //학과명에 소속된 학생들 조회(부전공)
	public ResponseEntity<List<StudentDto>> studenInDepartmentByDname2(@RequestParam("dname") String dname) {
		try {
			List<StudentDto> studDtoList = univService.studentListInDept2ByDeptName(dname);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);	
		}
	}
	
	//7. 학년으로 학생목록 조회
	@GetMapping("/studInfoByGrade")	//학년으로 학생목록 조회 
	public ResponseEntity<List<StudentDto>> studInfoByGrade(@RequestParam("grade") Integer grade) {
		try {
			List<StudentDto> studDtoList = univService.studentListInByGrade(grade);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//8. 담당교수가 없는 학생목록 조회
	@GetMapping("/studInNoProf") //담당교수가 없는 학생목록 조회
	public ResponseEntity<List<StudentDto>> studentInNoProfessor() {
		try {
			List<StudentDto> studDtoList = univService.studentListByNoProfessor();
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);			
		}
	}

	//9. 학번으로 학생조회	
	@GetMapping("/studInfoByNo")	//학번으로 학생 조회
	public ResponseEntity<StudentDto> studentInfoByNo(@RequestParam("studno") Integer studno) {
		try {
			StudentDto studDto = univService.studentByStudNo(studno);
			return new ResponseEntity<StudentDto>(studDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//10. 주민번호로 학생조회
	@PostMapping("/studInfoByJumin")	//주민번호로 학생 조회
	public ResponseEntity<StudentDto> studInfoByJumin(@RequestBody StudentDto studentDto) {
		//@RequestParam("jumin) String jumin 은 POST방식의 form-data로 조회해야 한다. 
		//@RequestBody로 json 데이터는 key-value 형태이므로 JSON 전체를 객체나 Map 형태로 받아야 한다.
		//맵 형태인 Map<String, String> requestData 파라미터로 사용하거나 
		//객체 형태인 StudentDto studentDto 로 사용해야 한다.
		try {
			StudentDto studDto = univService.studentByJumin(studentDto.getJumin());
			return new ResponseEntity<StudentDto>(studDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//11. 교수번호로 담당 학생목록 조회
	@GetMapping("/studInProfNo") 	//교수번호에 소속된 학생들 조회
	public ResponseEntity<List<StudentDto>> studentInProfessorByProfNo(@RequestParam("profno") Integer profno) {
		try {
			List<StudentDto> studDtoList = univService.studentListByProfNo(profno);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//12. 교수번호로 교수 조회
	@GetMapping("/profInfoByNo")	//교수번호로 교수조회
	public ResponseEntity<ProfessorDto> professorInfoByProfno(@RequestParam("profno") Integer profno) {
		try {
			ProfessorDto profDto = univService.professorByProfNo(profno);
			return new ResponseEntity<ProfessorDto>(profDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProfessorDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//13. 교수이름으로 교수 목록 조회
	@GetMapping("/profInfoByName")	//교수명으로 교수 조회
	public ResponseEntity<List<ProfessorDto>> professorInfoByName(@RequestParam("name") String name) {
		try {
			List<ProfessorDto> profDtoList = univService.professorListByProfName(name);
			return new ResponseEntity<List<ProfessorDto>>(profDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//14. 학과번호로 교수목록 조회
	@GetMapping("/profInDeptNo")	//학과번호에 소속된 교수 조회
	public ResponseEntity<List<ProfessorDto>> professorInDepartmentByDeptno(@RequestParam("deptno") Integer deptno) {
		try {
			List<ProfessorDto> profDtoList = univService.professorListByDeptNo(deptno);
			return new ResponseEntity<List<ProfessorDto>>(profDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//15. 학과이름으로 교수목록 조회
	@GetMapping("/profInDeptName")
	public ResponseEntity<List<ProfessorDto>> professorInDepartmentByDname(@RequestParam("dname") String dname) {
		try {
			List<ProfessorDto> profDtoList = univService.professorListByDeptName(dname);
			return new ResponseEntity<List<ProfessorDto>>(profDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//16. 직급으로 교수목록 조회
	@GetMapping("/profInfoByPosition")	
	public ResponseEntity<List<ProfessorDto>> professorInDepartmentByPosition(@RequestParam("position") String position) {
		try {
			List<ProfessorDto> profDtoList = univService.professorListByPosition(position);
			return new ResponseEntity<List<ProfessorDto>>(profDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);	
		}
	}
		
	//17. 학과번호로 학과 조회
	@GetMapping("/deptInfoByNo") 	//학과번호로 학과조회
	public ResponseEntity<DepartmentDto> departmentInfoByNo(@RequestParam("deptno") Integer deptno) {
		try {
			DepartmentDto deptDto = univService.departmentByDeptNo(deptno);
			return new ResponseEntity<DepartmentDto>(deptDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DepartmentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//18. 학과이름으로 학과 조회
	@GetMapping("/deptInfoByName")	//학과이름으로 학과조회
	public ResponseEntity<DepartmentDto> departmentInfoByDname(@RequestParam("dname") String dname) {
		try {
			DepartmentDto deptDto = univService.departmentByDeptName(dname);
			return new ResponseEntity<DepartmentDto>(deptDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DepartmentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//19. 학과(part)로 학과목록 조회
	@GetMapping("/deptInfoByPart")  //파트로 학과조회
	public ResponseEntity<List<DepartmentDto>> departmentInfoByPart(@RequestParam("part") Integer part) {
		try {
			List<DepartmentDto> deptDtoList = univService.departmentListByPart(part);
			return new ResponseEntity<List<DepartmentDto>>(deptDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DepartmentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//20. 위치하는 건물로 학과목록 조회
	@GetMapping("/deptInfoByBuild")  //건물로 학과조회
	public ResponseEntity<List<DepartmentDto>> departmentInfoByBuild(@RequestParam("build") String build) {
		try {
			List<DepartmentDto> deptDtoList = univService.departmentListByBuild(build);
			return new ResponseEntity<List<DepartmentDto>>(deptDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DepartmentDto>>(HttpStatus.BAD_REQUEST);
		}
	}

}
