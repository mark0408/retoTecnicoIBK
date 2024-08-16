package com.prueba.tecnica.demo.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
        throw new IllegalArgumentException("Codigo invalido: " + codigo);
    }

    public static int obtenerPorDescripcion(String descripcion) {
        for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
            if (tipo.getDescripcion().equals(descripcion)) {
                return tipo.getCodigo();
            }
        }
        return -1;
    }
}
