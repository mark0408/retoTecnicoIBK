package com.demo.ibk.authorization.service;

import com.demo.ibk.authorization.repository.RolRepository;
import com.demo.ibk.authorization.entity.Rol;
import com.demo.ibk.commons.enums.RolNombre;
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
