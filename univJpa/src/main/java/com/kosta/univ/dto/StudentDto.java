package com.kosta.univ.dto;

import java.sql.Date;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
	private Integer studno;
	private String name;
	private String id;
	private Integer grade;
	private String jumin;
	private Date birthday;
	private String tel;
	private Integer height;
	private Integer weight;
	
	private Integer deptno1;
	private String dname1; 	//extra
	private Integer deptno2;
	private String dname2;	//extra
	
	private Integer profno;	
	private String profname; //extra
	
	public Student toEntity() {
		Student student = Student.builder()
					  .studno(studno)
					  .name(name)
					  .id(id)
					  .grade(grade)
					  .jumin(jumin)
					  .birthday(birthday)
					  .tel(tel)
					  .height(height)
					  .weight(weight)
					  .build();
		if(deptno1 != null) {
			student.setDepartment1(Department.builder().deptno(deptno1).dname(dname1).build());
		}
		if(deptno2 != null) {
			student.setDepartment2(Department.builder().deptno(deptno2).dname(dname2).build());
		}
		if(profno != null) {
			student.setProfessor(Professor.builder().profno(profno).name(profname).build());
		}
		return student;
	}
	
}
