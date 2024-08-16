package com.prueba.tecnica.demo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCrearResponse implements Serializable {

    private static final long serialVersionUID = 5438686831990398681L;

    private Long id;
    private Double valorTipoCambio;
    private String nombres;
    private String apellidos;

}
