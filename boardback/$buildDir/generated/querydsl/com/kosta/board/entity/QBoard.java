package com.kosta.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1757963326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath content = createString("content");

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    public final QBFile imageFile;

    public final QMember member;

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath subject = createString("subject");

    public final QBFile uploadFile;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.imageFile = inits.isInitialized("imageFile") ? new QBFile(forProperty("imageFile"), inits.get("imageFile")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.uploadFile = inits.isInitialized("uploadFile") ? new QBFile(forProperty("uploadFile"), inits.get("uploadFile")) : null;
    }

}

