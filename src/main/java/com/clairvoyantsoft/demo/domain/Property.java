package com.clairvoyantsoft.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="properties")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Property.class)
public class Property {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String images;


    @ManyToOne
    @JoinColumn(name="fk_user")
    private User user;


    @OneToMany(mappedBy = "property")
    private Set<Booking> bookings = new HashSet<Booking>();

    @OneToMany(orphanRemoval = true)
    private Set<PropertyReview> reviews = new HashSet<PropertyReview>();

    @ManyToMany
    @JoinColumn(name="fk_property")
    private Set<Amenity> amenities = new HashSet<Amenity>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_property")
    private Address address;

    private void addBooking(final Booking booking){
        this.bookings.add(booking);
    }

    private void removeBooking(final Booking booking){
        this.bookings.remove(booking);
    }

    private void addReview(final PropertyReview propertyReview){
        this.reviews.add(propertyReview);
    }

    private void removeReview(final PropertyReview propertyReview){
        this.reviews.remove(propertyReview);
    }

    private void addAmenity(final Amenity amenity){
        this.amenities.add(amenity);
    }

    private void removeAmenity(final Amenity amenity){
        this.amenities.remove(amenity);
    }
}
