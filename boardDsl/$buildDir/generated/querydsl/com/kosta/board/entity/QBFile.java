package com.kosta.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBFile is a Querydsl query type for BFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBFile extends EntityPathBase<BFile> {

    private static final long serialVersionUID = -1759177254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBFile bFile = new QBFile("bFile");

    public final QBoard boardImg;

    public final QBoard boardUpload;

    public final StringPath contentType = createString("contentType");

    public final StringPath directory = createString("directory");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final DatePath<java.sql.Date> uploadDate = createDate("uploadDate", java.sql.Date.class);

    public QBFile(String variable) {
        this(BFile.class, forVariable(variable), INITS);
    }

    public QBFile(Path<? extends BFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBFile(PathMetadata metadata, PathInits inits) {
        this(BFile.class, metadata, inits);
    }

    public QBFile(Class<? extends BFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardImg = inits.isInitialized("boardImg") ? new QBoard(forProperty("boardImg"), inits.get("boardImg")) : null;
        this.boardUpload = inits.isInitialized("boardUpload") ? new QBoard(forProperty("boardUpload"), inits.get("boardUpload")) : null;
    }

}

