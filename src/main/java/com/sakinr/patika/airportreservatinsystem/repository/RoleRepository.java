package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
