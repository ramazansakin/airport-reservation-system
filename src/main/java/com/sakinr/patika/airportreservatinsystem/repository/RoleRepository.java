package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
