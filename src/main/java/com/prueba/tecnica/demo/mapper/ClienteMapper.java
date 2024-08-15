package com.prueba.tecnica.demo.mapper;

import com.prueba.tecnica.demo.domain.Cliente;
import com.prueba.tecnica.demo.dto.ClienteDTO;
import com.prueba.tecnica.demo.util.EncriptarUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.prueba.tecnica.demo.enums.TipoDocumentoEnum;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);
    @Mappings({
            @Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedByName = "mapTipoDocumentoToString"),
            @Mapping(source = "codigoUnico", target = "codigoUnico", qualifiedByName = "encriptarCodigoUnico")
    })
    ClienteDTO clienteToClienteDto(Cliente cliente);

    @Mappings({
            @Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedByName = "mapStringToTipoDocumento")
    })
    Cliente clienteDtoToCliente(ClienteDTO clienteDTO);

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
