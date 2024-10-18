package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kosta.univ.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {
	@Id
	private Integer studno;
	@Column
	private String name;
	@Column
	private String id;
	@Column
	private Integer grade;
	@Column
	private String jumin;
	@Column
	private Date birthday;
	@Column
	private String tel;
	@Column
	private Integer height;
	@Column
	private Integer weight;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deptno1")
	private Department department1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deptno2")
	private Department department2;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profno")
	private Professor professor;
	
	public StudentDto toDto() {
		StudentDto studentDto =  StudentDto.builder()
						 .studno(studno)
						 .name(name)
						 .id(id)
						 .grade(grade)
						 .jumin(jumin)
						 .birthday(birthday)
						 .tel(tel)
						 .height(height)
						 .weight(weight)
						 .deptno1(department1.getDeptno())
						 .dname1(department1.getDname()).build();
		
		if(department2 != null) {
			studentDto.setDeptno2(department2.getDeptno());
			studentDto.setDname2(department2.getDname());
		}
		if(professor != null) {
			studentDto.setProfno(professor.getProfno());
			studentDto.setProfname(professor.getName());
		}
		return studentDto;
	}
	
	
//	public StudentDto toDto() {
//		return StudentDto.builder()
//						 .studno(studno)
//						 .name(name)
//						 .id(id)
//						 .grade(grade)
//						 .jumin(jumin)
//						 .birthday(birthday)
//						 .tel(tel)
//						 .height(height)
//						 .weight(weight)
//						 .deptno1(department1 != null ? department1.getDeptno() : null)
//						 .dname1(department1 != null ? department1.getDname() : null)
//						 .deptno2(department2 != null ? department2.getDeptno() : null)
//						 .dname2(department2 != null ? department2.getDname() : null)
//						 .profno(professor != null ? professor.getProfno() : null)
//						 .profname(professor != null ? professor.getName() : null)
//						 .build();
//	}
	
	
}
