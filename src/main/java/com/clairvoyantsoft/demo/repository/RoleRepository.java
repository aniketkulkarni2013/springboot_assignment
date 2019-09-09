package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.Role;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> , QuerydslPredicateExecutor<Role> {


}
