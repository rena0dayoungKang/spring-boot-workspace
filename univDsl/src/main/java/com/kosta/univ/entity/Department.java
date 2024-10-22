package com.kosta.univ.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.univ.dto.DepartmentDto;

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
public class Department {
	@Id
	private Integer deptno;
	@Column
	private String dname;
	@Column
	private Integer part;
	@Column
	private String build;

	public DepartmentDto toDto() {
		return DepartmentDto.builder()
						    .deptno(deptno)
						    .dname(dname)
						    .part(part)
						    .build(build)
						    .build();
	}
}
