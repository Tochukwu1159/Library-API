package com.codewithchristian.libraryapi.repositories.users;


import com.codewithchristian.libraryapi.models.users.Role;
import com.codewithchristian.libraryapi.models.users.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
