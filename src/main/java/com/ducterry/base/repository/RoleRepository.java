package com.ducterry.base.repository;


import com.ducterry.base.entity.login.Role;
import com.ducterry.base.enums.ERoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoleName name);
}
