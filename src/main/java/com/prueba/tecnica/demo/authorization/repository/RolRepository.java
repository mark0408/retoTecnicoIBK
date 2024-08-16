package com.prueba.tecnica.demo.authorization.repository;

import com.prueba.tecnica.demo.authorization.entity.Rol;
import com.prueba.tecnica.demo.commons.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
  Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
