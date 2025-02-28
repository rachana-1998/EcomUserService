package com.rachana.EcomUserService.repo;

import com.rachana.EcomUserService.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role ,Long > {
    List<Role> findByIdIn(List<Long> roleIds);
}
