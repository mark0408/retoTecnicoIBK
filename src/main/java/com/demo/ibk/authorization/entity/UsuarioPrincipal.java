package com.demo.ibk.authorization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPrincipal implements UserDetails {

  private String nombre;
  private String nombreUsuario;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UsuarioPrincipal build(Usuario usuario){
    List<GrantedAuthority> authorities =
            usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                    .getRolNombre().name())).collect(Collectors.toList());
    return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
  }

  @Override
  public String getUsername() {
    return nombreUsuario;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
