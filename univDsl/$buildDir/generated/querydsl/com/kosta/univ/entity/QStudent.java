package com.kosta.univ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = -2010549351L;

    public static final QStudent student = new QStudent("student");

    public final DatePath<java.sql.Date> birthday = createDate("birthday", java.sql.Date.class);

    public final NumberPath<Integer> deptno1 = createNumber("deptno1", Integer.class);

    public final NumberPath<Integer> deptno2 = createNumber("deptno2", Integer.class);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final StringPath id = createString("id");

    public final StringPath jumin = createString("jumin");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> profno = createNumber("profno", Integer.class);

    public final NumberPath<Integer> studno = createNumber("studno", Integer.class);

    public final StringPath tel = createString("tel");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QStudent(String variable) {
        super(Student.class, forVariable(variable));
    }

    public QStudent(Path<? extends Student> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudent(PathMetadata metadata) {
        super(Student.class, metadata);
    }

}

