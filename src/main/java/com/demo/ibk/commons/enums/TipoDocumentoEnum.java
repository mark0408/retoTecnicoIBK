package com.demo.ibk.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoDocumentoEnum {
    DNI(1, "DNI"),
    PASAPORTE(2, "PASAPORTE");

    private final int codigo;
    private final String descripcion;

    public static String obtenerPorCodigo(int codigo) {
        return Arrays.stream(TipoDocumentoEnum.values())
                .filter(tipo -> tipo.getCodigo() == codigo)
                .map(TipoDocumentoEnum::getDescripcion)
                .findFirst()
                .orElse("-1");
    }

    public static int obtenerPorDescripcion(String descripcion) {
        return Arrays.stream(TipoDocumentoEnum.values())
                .filter(tipo -> tipo.getDescripcion().equals(descripcion))
                .map(TipoDocumentoEnum::getCodigo)
                .findFirst()
                .orElse(-1);
    }
}
