package com.clairvoyantsoft.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooking is a Querydsl query type for Booking
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBooking extends EntityPathBase<Booking> {

    private static final long serialVersionUID = -600027311L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooking booking = new QBooking("booking");

    public final NumberPath<java.math.BigDecimal> amountPaid = createNumber("amountPaid", java.math.BigDecimal.class);

    public final StringPath bookingStatus = createString("bookingStatus");

    public final NumberPath<java.math.BigDecimal> discount = createNumber("discount", java.math.BigDecimal.class);

    public final DateTimePath<java.time.Instant> endDate = createDateTime("endDate", java.time.Instant.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPayment payment;

    public final StringPath paymentStatus = createString("paymentStatus");

    public final QProperty property;

    public final DateTimePath<java.time.Instant> startDate = createDateTime("startDate", java.time.Instant.class);

    public final NumberPath<java.math.BigDecimal> tax = createNumber("tax", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public final QUser user;

    public QBooking(String variable) {
        this(Booking.class, forVariable(variable), INITS);
    }

    public QBooking(Path<? extends Booking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooking(PathMetadata metadata, PathInits inits) {
        this(Booking.class, metadata, inits);
    }

    public QBooking(Class<? extends Booking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

