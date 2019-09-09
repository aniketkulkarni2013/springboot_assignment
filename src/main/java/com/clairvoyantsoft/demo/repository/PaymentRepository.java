package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.Payment;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long>  , QuerydslPredicateExecutor<Payment> {


}
