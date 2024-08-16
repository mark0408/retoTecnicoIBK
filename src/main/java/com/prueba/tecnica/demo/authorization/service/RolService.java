package com.prueba.tecnica.demo.authorization.service;

import com.prueba.tecnica.demo.authorization.entity.Rol;
import com.prueba.tecnica.demo.commons.enums.RolNombre;
import com.prueba.tecnica.demo.authorization.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

  @Autowired
  RolRepository rolRepository;

  public Optional<Rol> getByRolNombre(RolNombre rolNombre){
    return rolRepository.findByRolNombre(rolNombre);
  }

  public void save(Rol rol){
    rolRepository.save(rol);
  }
}
