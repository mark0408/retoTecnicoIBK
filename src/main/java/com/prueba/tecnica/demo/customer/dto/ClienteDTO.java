package com.prueba.tecnica.demo.customer.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
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
 // @Pattern()
  private String tipoDocumento;
  private String numeroDocumento;
}
