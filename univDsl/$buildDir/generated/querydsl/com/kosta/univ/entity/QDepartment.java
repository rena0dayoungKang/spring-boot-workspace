package com.kosta.univ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -1076073164L;

    public static final QDepartment department = new QDepartment("department");

    public final StringPath build = createString("build");

    public final NumberPath<Integer> deptno = createNumber("deptno", Integer.class);

    public final StringPath dname = createString("dname");

    public final NumberPath<Integer> part = createNumber("part", Integer.class);

    public QDepartment(String variable) {
        super(Department.class, forVariable(variable));
    }

    public QDepartment(Path<? extends Department> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepartment(PathMetadata metadata) {
        super(Department.class, metadata);
    }

}

