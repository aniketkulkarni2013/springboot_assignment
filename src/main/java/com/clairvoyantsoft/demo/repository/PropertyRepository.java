package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.Property;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property,Long> , QuerydslPredicateExecutor<Property> {

   List<Property> findByAddressZipcode(String zipcode);
}
