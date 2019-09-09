package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long>, QuerydslPredicateExecutor<User> {

    Optional<User> findOneByUsername(String username);

    @Query(value = "SELECT u FROM User u INNER JOIN FETCH u.payments p where u.username=:username")
     User findUserByUsername(@Param("username") String username);
}
