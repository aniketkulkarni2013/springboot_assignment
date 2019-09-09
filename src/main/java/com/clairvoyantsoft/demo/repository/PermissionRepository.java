package com.clairvoyantsoft.demo.repository;

import com.clairvoyantsoft.demo.domain.Booking;
import com.clairvoyantsoft.demo.domain.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository  extends CrudRepository<Permission,Long> {
}
