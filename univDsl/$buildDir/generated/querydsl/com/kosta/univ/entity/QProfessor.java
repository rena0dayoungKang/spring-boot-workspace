package com.kosta.univ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfessor is a Querydsl query type for Professor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfessor extends EntityPathBase<Professor> {

    private static final long serialVersionUID = 1561454669L;

    public static final QProfessor professor = new QProfessor("professor");

    public final NumberPath<Integer> bonus = createNumber("bonus", Integer.class);

    public final NumberPath<Integer> deptno = createNumber("deptno", Integer.class);

    public final StringPath email = createString("email");

    public final DatePath<java.sql.Date> hiredate = createDate("hiredate", java.sql.Date.class);

    public final StringPath hpage = createString("hpage");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> pay = createNumber("pay", Integer.class);

    public final StringPath position = createString("position");

    public final NumberPath<Integer> profno = createNumber("profno", Integer.class);

    public QProfessor(String variable) {
        super(Professor.class, forVariable(variable));
    }

    public QProfessor(Path<? extends Professor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfessor(PathMetadata metadata) {
        super(Professor.class, metadata);
    }

}

