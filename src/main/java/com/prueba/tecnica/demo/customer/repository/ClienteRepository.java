package com.prueba.tecnica.demo.customer.repository;

import com.prueba.tecnica.demo.customer.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

  Optional<ClienteEntity> findByCodigoUnico(String codigoUnico);
}
