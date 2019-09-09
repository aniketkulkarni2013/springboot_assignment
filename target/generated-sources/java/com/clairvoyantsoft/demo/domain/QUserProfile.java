package com.clairvoyantsoft.demo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserProfile is a Querydsl query type for UserProfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserProfile extends EntityPathBase<UserProfile> {

    private static final long serialVersionUID = -2088405418L;

    public static final QUserProfile userProfile = new QUserProfile("userProfile");

    public final StringPath emailId = createString("emailId");

    public final StringPath firstname = createString("firstname");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QUserProfile(String variable) {
        super(UserProfile.class, forVariable(variable));
    }

    public QUserProfile(Path<? extends UserProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProfile(PathMetadata metadata) {
        super(UserProfile.class, metadata);
    }

}

