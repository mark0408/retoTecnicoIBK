package com.prueba.tecnica.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum TipoDocumentoEnum {
    DNI(1, "DNI"),
    PASAPORTE(2, "PASAPORTE");

    private final int codigo;
    private final String descripcion;

    public static TipoDocumentoEnum obtenerPorCodigo(int codigo) {
        for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + codigo);
    }
}
