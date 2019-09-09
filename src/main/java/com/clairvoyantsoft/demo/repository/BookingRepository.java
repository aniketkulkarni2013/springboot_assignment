package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> , QuerydslPredicateExecutor<Booking> {

    <T> List<T> findBy(Class<T> projection);

    @Query(value = "SELECT b FROM Booking b INNER JOIN FETCH b.property p where b.startDate <= :startTime and :endTime <= b.endDate and p.id=:propertyId ")
    List<Booking> findIfBookingExists(@Param("propertyId")Long propertyId,@Param("startTime") Instant startTime,@Param("endTime") Instant endTime);


}
