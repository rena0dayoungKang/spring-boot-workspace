package com.kosta.univ.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.QDepartment;
import com.kosta.univ.entity.QProfessor;
import com.kosta.univ.entity.QStudent;
import com.kosta.univ.entity.Student;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UnivDslRepository {

	private final DepartmentRepository departmentRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//1. 부서등록
	public void insertDept(Department department) throws Exception {
		departmentRepository.save(department);
	}
	
	//2. 학생등록
	public void insertStudent(Student student) throws Exception {
		studentRepository.save(student);
	}
	
	//3. 교수등록
	public void insertProf(Professor professor) throws Exception {
		professorRepository.save(professor);
	}
	
	//4. 학생 이름으로 학생목록 조회
	public List<Student> selectStudentByName(String name) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.name.eq(name))
							  .fetch();
	}
	
	//5. 제1전공 번호로 학생목록 조회
	public List<Student> selectStudListByDeptno1(Integer deptno1) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.deptno1.eq(deptno1))
							  .fetch();
	}
	
	//5. 제1전공 [이름]으로 학생목록 조회
	public List<Tuple> selectStudListWithDeptname1(String dname1) throws Exception {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(student, department.dname)
							  .from(student)
							  .join(department)
							  .on(student.deptno1.eq(department.deptno))
							  .where(department.dname.eq(dname1))
							  .fetch();
	}
	
	//6. 제2전공 번호로 학생목록 조회
	public List<Student> selectStudListByDeptno2(Integer deptno2) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.deptno2.eq(deptno2))
							  .fetch();
	}
	
	//6. 제2전공 [이름]으로 학생목록 조회
	public List<Tuple> selectStudListWithDeptname2(String dname2) throws Exception {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(student, department.dname)
							  .from(student)
							  .join(department)
							  .on(student.deptno2.eq(department.deptno))
							  .where(department.dname.eq(dname2))
							  .fetch();
	}
	
	//7. 학년으로 학생목록 조회
	public List<Student> selectStudListByGrade(Integer grade) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.grade.eq(grade))
							  .fetch();
	}
	
	//8. 담당교수가 없는 학생목록 조회
	public List<Student> selectStudListByNoProf() throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.profno.isNull())
							  .fetch();
	}
	
	//9. 학번으로 학생조회
	public Student selectStudentByStudNo(Integer studNo) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.studno.eq(studNo))
							  .fetchOne();
	}
	
	//10. 주민번호로 학생조회
	public Student selectStudByJumin(String jumin) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.jumin.eq(jumin))
							  .fetchOne();
	}
	
	//11. 교수번호로 담당 학생목록 조회
	public List<Student> selectStudListByProfNo(Integer profno) throws Exception {
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(student)
							  .from(student)
							  .where(student.profno.eq(profno))
							  .fetch();
	}
	
	//12. 교수번호로 교수 조회
	public Professor selectProfByProfNo(Integer profno) throws Exception {
		QProfessor professor = QProfessor.professor;
		return jpaQueryFactory.select(professor)
							  .from(professor)
							  .where(professor.profno.eq(profno))
							  .fetchOne();
	}
	
	//13. 교수이름으로 교수 목록 조회
	public List<Professor> selectProfListByProfname(String profname) throws Exception {
		QProfessor professor = QProfessor.professor;
		return jpaQueryFactory.select(professor)
							  .from(professor)
							  .where(professor.name.eq(profname))
							  .fetch();
	}
	
	//14. 학과번호로 교수목록 조회
	public List<Professor> selectProfListByDeptno(Integer deptno) throws Exception {
		QProfessor professor = QProfessor.professor;
		return jpaQueryFactory.select(professor)
							  .from(professor)
							  .where(professor.deptno.eq(deptno))
							  .fetch();
	}
	
	//15. 학과이름으로 교수목록 조회
	public List<Professor> selectProfListByDeptname(String dname) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(professor)
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(department.dname.eq(dname))
							  .fetch();
	}
	
	//16. 직급으로 교수목록 조회
	public List<Professor> selectProfListByPosition(String position) throws Exception {
		QProfessor professor = QProfessor.professor;
		return jpaQueryFactory.select(professor)
							  .from(professor)
							  .where(professor.position.eq(position))
							  .fetch();
	}
	
	//17. 학과번호로 학과 조회
	public Department selectDeptByDeptNo(Integer deptNo) throws Exception {
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(department)
							  .from(department)
							  .where(department.deptno.eq(deptNo))
							  .fetchOne();
	}
	
	//18. 학과이름으로 학과 조회
	public Department selectDeptByDname(String dname) throws Exception {
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(department)
							  .from(department)
							  .where(department.dname.eq(dname))
							  .fetchOne();
									  
	}
	
	//19. 학과(part)로 학과목록 조회
	public List<Department> selectDeptByPart(Integer part) throws Exception {
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(department)
							  .from(department)
							  .where(department.part.eq(part))
							  .fetch();
	}
	
	//20. 위치하는 건물로 학과목록 조회
	public List<Department> selectDeptByBuild(String build) throws Exception {
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(department)
							  .from(department)
							  .where(department.build.eq(build))
							  .fetch();
	}
}
