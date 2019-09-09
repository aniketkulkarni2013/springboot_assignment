package com.clairvoyantsoft.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name= "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Property> properties = new HashSet<Property>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Booking> bookings = new HashSet<Booking>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_user")
    private Set<PropertyReview> reviews = new HashSet<PropertyReview>();

    @OneToOne
    @JoinColumn(name="fk_user_profile")
    private UserProfile userProfile;

    @ToString.Exclude
    @ManyToMany(cascade =  CascadeType.ALL)
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Payment> payments = new HashSet<Payment>();

    public void addProperty(final Property property){
        this.properties.add(property);
    }

    public void removeProperty(final Property property){
        this.properties.remove(property);
    }

    public void addBooking(final Booking booking){
        this.bookings.add(booking);
    }

    public void removeBooking(final Booking booking){
        this.bookings.remove(booking);
    }

    public void addReview(final PropertyReview propertyReview){
        this.reviews.add(propertyReview);
    }

    public void removeReview(final PropertyReview propertyReview){
        this.reviews.remove(propertyReview);
    }

    public void addRole(final Role role) {
        this.roles.add(role);
    }

    public void removeRole(final Role role){
        this.roles.remove(role);
    }

    public void addPayment(final Payment payment){
        this.payments.add(payment);
    }

    public void removePayment(final Payment payment){
        this.payments.remove(payment);
    }


}
