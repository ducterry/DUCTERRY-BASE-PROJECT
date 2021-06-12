package com.ducterry.base.ducterrybase.repository;


import com.ducterry.base.ducterrybase.entity.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(com.ndangducbn.base.dao.enums.ERoleName name);
}
