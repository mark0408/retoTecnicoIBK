package com.prueba.tecnica.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteListarRequest implements Serializable {

    private static final long serialVersionUID = 5438686831990398681L;

    private String codigoUnico;


}
