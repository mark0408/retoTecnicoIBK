package com.demo.ibk.customer.mapper;

import com.demo.ibk.customer.dto.CustomerDTO;
import com.demo.ibk.customer.entity.CustomerEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.demo.ibk.commons.enums.TipoDocumentoEnum;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "uniqueCode", target = "codigoUnico")
    @Mapping(source = "names", target = "nombres")
    @Mapping(source = "surnames", target = "apellidos")
    @Mapping(source = "typeDocument", target = "tipoDocumento", qualifiedByName = "mapTypeDocumentString")
    @Mapping(source = "numberDocument", target = "numeroDocumento")
    CustomerDTO toDto(CustomerEntity customerEntity);

    @Mapping(source = "codigoUnico", target = "uniqueCode")
    @Mapping(source = "nombres", target = "names")
    @Mapping(source = "apellidos", target = "surnames")
    @Mapping(source = "tipoDocumento", target = "typeDocument", qualifiedByName = "mapTypeDocumentInteger")
    @Mapping(source = "numeroDocumento", target = "numberDocument")
    CustomerEntity toEntity(CustomerDTO customerDTO);


    @Named("mapTypeDocumentString")
    default String mapTypeDocumentString(Integer typeDocument) {
        return TipoDocumentoEnum.obtenerPorCodigo(typeDocument);
    }

    @Named("mapTypeDocumentInteger")
    default Integer mapTypeDocumentInteger(String typeDocument) {
        return TipoDocumentoEnum.obtenerPorDescripcion(typeDocument);
    }
}
