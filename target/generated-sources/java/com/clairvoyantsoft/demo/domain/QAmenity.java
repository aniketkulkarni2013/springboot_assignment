package com.clairvoyantsoft.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAmenity is a Querydsl query type for Amenity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAmenity extends EntityPathBase<Amenity> {

    private static final long serialVersionUID = -1553934927L;

    public static final QAmenity amenity = new QAmenity("amenity");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<Property, QProperty> property = this.<Property, QProperty>createSet("property", Property.class, QProperty.class, PathInits.DIRECT2);

    public QAmenity(String variable) {
        super(Amenity.class, forVariable(variable));
    }

    public QAmenity(Path<? extends Amenity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmenity(PathMetadata metadata) {
        super(Amenity.class, metadata);
    }

}

