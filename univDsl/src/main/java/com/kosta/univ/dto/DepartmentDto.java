package com.kosta.univ.dto;


import com.kosta.univ.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
	private Integer deptno;
	private String dname;
	private Integer part;
	private String build;
	
	public Department toEntity() {
		return Department.builder()
						 .deptno(deptno)
						 .dname(dname)
						 .part(part)
						 .build(build)
						 .build();
	}
	
}
