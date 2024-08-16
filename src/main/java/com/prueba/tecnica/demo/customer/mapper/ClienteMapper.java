package com.prueba.tecnica.demo.customer.mapper;

import com.prueba.tecnica.demo.customer.entity.ClienteEntity;
import com.prueba.tecnica.demo.customer.dto.ClienteDTO;
import com.prueba.tecnica.demo.commons.util.EncriptarUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.prueba.tecnica.demo.commons.enums.TipoDocumentoEnum;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);

    @Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedByName = "mapTipoDocumentoToString")
    @Mapping(source = "codigoUnico", target = "codigoUnico", qualifiedByName = "encriptarCodigoUnico")
    ClienteDTO clienteToClienteDto(ClienteEntity clienteEntity);


    @Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedByName = "mapStringToTipoDocumento")
    ClienteEntity clienteDtoToCliente(ClienteDTO clienteDTO);

    @Named("encriptarCodigoUnico")
    default String encriptarCodigoUnico(String codigoUnico) {
        return EncriptarUtils.Encriptar(codigoUnico);
    }
    @Named("mapTipoDocumentoToString")
    default String mapTipoDocumentoToString(Integer tipoDocumento) {
        return TipoDocumentoEnum.obtenerPorCodigo(tipoDocumento).getDescripcion();
    }

    @Named("mapStringToTipoDocumento")
    default Integer mapStringToTipoDocumento(String tipoDocumento) {
        for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
            if (tipo.getDescripcion().equals(tipoDocumento)) {
                return tipo.getCodigo();
            }
        }
        throw new IllegalArgumentException("Invalid description: " + tipoDocumento);
    }
}
