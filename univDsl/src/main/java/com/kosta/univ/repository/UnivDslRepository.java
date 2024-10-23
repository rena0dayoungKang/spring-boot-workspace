package com.kosta.univ.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.QDepartment;
import com.kosta.univ.entity.QProfessor;
import com.kosta.univ.entity.QStudent;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
//join되는 쿼리들을 univ repo에서 처리했음, 단순 find, save는 각각의 repo에서 처리
public class UnivDslRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//4. 학생 이름으로 학생목록 조회 <!-- tuple을 안쓰는 방법 -->
	public List<StudentDto> selectStudListByName(String name) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(Projections.bean(StudentDto.class, 
													   student.studno,
													   student.name,
													   student.id,
													   student.grade,
													   student.jumin,
													   student.birthday,
													   student.tel,
													   student.height,
													   student.weight,
													   student.deptno1,
													   student.deptno2,
													   student.profno,
													   depart1.dname.as("dname1"),
													   depart2.dname.as("dname2"),
													   professor.name.as("profname")))
								.from(student)
								.leftJoin(depart1)
								.on(student.deptno1.eq(depart1.deptno))
								.leftJoin(depart2)
								.on(student.deptno2.eq(depart2.deptno))
								.leftJoin(professor)
								.on(student.profno.eq(professor.profno))
								.where(student.name.eq(name))
								.fetch();
	}
	
	//5. 제1전공 번호로 학생목록 조회
	public List<Tuple> selectStudListByDeptno1(Integer deptno1) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(depart1.deptno.eq(deptno1))
							  .fetch();
	}
	
	//5. 제1전공 [이름]으로 학생목록 조회
	public List<Tuple> selectStudListWithDeptname1(String dname1) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(depart1.dname.eq(dname1))
							  .fetch();
	}
	
	//6. 제2전공 번호로 학생목록 조회
	public List<Tuple> selectStudListByDeptno2(Integer deptno2) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(depart2.deptno.eq(deptno2))
							  .fetch();
	}
	
	//6. 제2전공 [이름]으로 학생목록 조회
	public List<Tuple> selectStudListWithDeptname2(String dname2) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(depart2.dname.eq(dname2))
							  .fetch();
	}
	
	//7. 학년으로 학생목록 조회
	public List<Tuple> selectStudListByGrade(Integer grade) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(student.grade.eq(grade))
							  .fetch();
	}
	
	//8. 담당교수가 없는 학생목록 조회
	public List<Tuple> selectStudListByNoProf() throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		//QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .where(student.profno.isNull())
							  .fetch();
	}
	
	//9. 학번으로 학생조회
	public Tuple selectStudentByStudNo(Integer studNo) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(student.studno.eq(studNo))
							  .fetchOne();
	}
	
	//10. 주민번호로 학생조회
	public Tuple selectStudByJumin(String jumin) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(student.jumin.eq(jumin))
							  .fetchOne();
	}
	
	//11. 교수번호로 담당 학생목록 조회
	public List<Tuple> selectStudListByProfNo(Integer profno) throws Exception {
		QStudent student = QStudent.student;
		QDepartment depart1 = new QDepartment("department1");
		QDepartment depart2 = new QDepartment("department2");
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, depart1.dname, depart2.dname, professor.name)
							  .from(student)
							  .leftJoin(depart1)
							  .on(student.deptno1.eq(depart1.deptno))
							  .leftJoin(depart2)
							  .on(student.deptno2.eq(depart2.deptno))
							  .leftJoin(professor)
							  .on(student.profno.eq(professor.profno))
							  .where(student.profno.eq(profno))
							  .fetch();
	}
	
	//12. 교수번호로 교수 조회
	public Tuple selectProfByProfNo(Integer profno) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(professor, department.dname)
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(professor.profno.eq(profno))
							  .fetchOne();
	}
	
	//13. 교수이름으로 교수 목록 조회 <!-- 이런 방법도 가능함 -->
	public List<ProfessorDto> selectProfListByProfname(String profname) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(Projections.bean(ProfessorDto.class, 
												  		  professor.profno,
														  professor.name,
														  professor.id,
														  professor.position,
														  professor.pay,
														  professor.hiredate,
														  professor.bonus,
														  professor.email,
														  professor.hpage,
														  professor.deptno,
														  department.dname))	
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(professor.name.eq(profname))
							  .fetch();
	}
	
	//14. 학과번호로 교수목록 조회
	public List<Tuple> selectProfListByDeptno(Integer deptno) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(professor, department.dname)
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(professor.deptno.eq(deptno))
							  .fetch();
	}
	
	//15. 학과이름으로 교수목록 조회
	public List<Tuple> selectProfListByDeptname(String dname) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(professor, department.dname)
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(department.dname.eq(dname))
							  .fetch();
	}
	
	//16. 직급으로 교수목록 조회
	public List<Tuple> selectProfListByPosition(String position) throws Exception {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(professor, department.dname)
							  .from(professor)
							  .leftJoin(department)
							  .on(professor.deptno.eq(department.deptno))
							  .where(professor.position.eq(position))
							  .fetch();
	}
}
