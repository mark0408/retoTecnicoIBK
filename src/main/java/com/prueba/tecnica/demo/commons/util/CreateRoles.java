package com.prueba.tecnica.demo.commons.util;

import com.prueba.tecnica.demo.authorization.entity.Rol;
import com.prueba.tecnica.demo.authorization.entity.Usuario;
import com.prueba.tecnica.demo.commons.enums.RolNombre;
import com.prueba.tecnica.demo.authorization.service.RolService;
import com.prueba.tecnica.demo.authorization.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateRoles implements CommandLineRunner {

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  RolService rolService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
     Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
     Rol rolUser = new Rol(RolNombre.ROLE_USER);
     rolService.save(rolAdmin);
     rolService.save(rolUser);

    Usuario usuario =
            new Usuario("admin", "admin", "admin@gmail.com",
                    passwordEncoder.encode("admin"));
    Set<Rol> roles = new HashSet<>();
    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    usuario.setRoles(roles);
    usuarioService.save(usuario);
  }
}
