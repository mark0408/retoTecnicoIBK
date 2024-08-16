package com.demo.ibk.authorization.repository;

import com.demo.ibk.authorization.entity.Rol;
import com.demo.ibk.commons.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
  Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
