package com.kosta.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardLike is a Querydsl query type for BoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardLike extends EntityPathBase<BoardLike> {

    private static final long serialVersionUID = -1228665223L;

    public static final QBoardLike boardLike = new QBoardLike("boardLike");

    public final NumberPath<Integer> boardNum = createNumber("boardNum", Integer.class);

    public final StringPath memId = createString("memId");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public QBoardLike(String variable) {
        super(BoardLike.class, forVariable(variable));
    }

    public QBoardLike(Path<? extends BoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardLike(PathMetadata metadata) {
        super(BoardLike.class, metadata);
    }

}

