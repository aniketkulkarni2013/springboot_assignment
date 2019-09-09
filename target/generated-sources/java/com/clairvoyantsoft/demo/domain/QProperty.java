package com.clairvoyantsoft.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProperty is a Querydsl query type for Property
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProperty extends EntityPathBase<Property> {

    private static final long serialVersionUID = -124422691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProperty property = new QProperty("property");

    public final QAddress address;

    public final SetPath<Amenity, QAmenity> amenities = this.<Amenity, QAmenity>createSet("amenities", Amenity.class, QAmenity.class, PathInits.DIRECT2);

    public final SetPath<Booking, QBooking> bookings = this.<Booking, QBooking>createSet("bookings", Booking.class, QBooking.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath images = createString("images");

    public final StringPath name = createString("name");

    public final SetPath<PropertyReview, QPropertyReview> reviews = this.<PropertyReview, QPropertyReview>createSet("reviews", PropertyReview.class, QPropertyReview.class, PathInits.DIRECT2);

    public final QUser user;

    public QProperty(String variable) {
        this(Property.class, forVariable(variable), INITS);
    }

    public QProperty(Path<? extends Property> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProperty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProperty(PathMetadata metadata, PathInits inits) {
        this(Property.class, metadata, inits);
    }

    public QProperty(Class<? extends Property> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

