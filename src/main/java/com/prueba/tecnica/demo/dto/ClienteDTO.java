package com.prueba.tecnica.demo.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

  private static final long serialVersionUID = 5438686831991398681L;
  private Long id;
  private String codigoUnico;
  private String nombres;
  private String apellidos;
  private String tipoDocumento;
  private String numeroDocumento;
}
