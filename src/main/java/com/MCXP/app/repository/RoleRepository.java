package com.MCXP.app.repository;

import com.MCXP.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getRolesByName(String name);
}
