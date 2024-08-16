package com.demo.ibk.customer.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {

  private static final long serialVersionUID = 5438686831991398681L;

  @NotNull
  @NotBlank
  private String codigoUnico;
  private String nombres;
  private String apellidos;
  @Pattern(regexp = "^(DNI|PASAPORTE)$")
  private String tipoDocumento;
  private String numeroDocumento;
}
