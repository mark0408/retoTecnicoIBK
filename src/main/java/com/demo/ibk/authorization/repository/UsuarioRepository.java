package com.demo.ibk.authorization.repository;

import com.demo.ibk.authorization.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByNombreUsuario(String nombreUsuario);
  boolean existsByNombreUsuario(String nombreUsuario);
  boolean existsByEmail(String email);
}
