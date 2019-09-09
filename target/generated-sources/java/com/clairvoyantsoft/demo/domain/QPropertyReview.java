package com.clairvoyantsoft.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPropertyReview is a Querydsl query type for PropertyReview
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPropertyReview extends EntityPathBase<PropertyReview> {

    private static final long serialVersionUID = -362889323L;

    public static final QPropertyReview propertyReview = new QPropertyReview("propertyReview");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath review = createString("review");

    public QPropertyReview(String variable) {
        super(PropertyReview.class, forVariable(variable));
    }

    public QPropertyReview(Path<? extends PropertyReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPropertyReview(PathMetadata metadata) {
        super(PropertyReview.class, metadata);
    }

}

