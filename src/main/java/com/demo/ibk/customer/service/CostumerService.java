package com.demo.ibk.customer.service;


import com.demo.ibk.commons.util.encryption.EncryptionContext;
import com.demo.ibk.customer.entity.CustomerEntity;
import com.demo.ibk.customer.mapper.CustomerMapper;
import com.demo.ibk.customer.repository.CustomerRepository;
import com.demo.ibk.commons.enums.TipoDocumentoEnum;
import com.demo.ibk.customer.dto.CustomerDTO;
import com.demo.ibk.commons.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class CostumerService implements ICustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final EncryptionContext encryptionContext;

  public CustomerDTO getByUniqueCode(String request){
    CustomerDTO response = new CustomerDTO();
    Optional<CustomerEntity> cliente = customerRepository.findByUniqueCode(request);
    if(cliente.isPresent()){
      response = customerMapper.toDto(cliente.get());
    }else{
      throw new ValidationException("Error-401","Error no se encuentra Cliente");
    }

    return response;
  }

  public CustomerDTO save(CustomerDTO request){
    CustomerDTO response = new CustomerDTO();
    request.setCodigoUnico(encryptionContext.encrypt(request.getCodigoUnico()));
    Optional<CustomerEntity> optionalCliente = customerRepository.findByUniqueCode(request.getCodigoUnico());
    if(optionalCliente.isPresent()){
      throw new ValidationException("Error-402","Ya existe un valor unico para este Cliente");
    }
    CustomerEntity customerEntityTemp = customerMapper.toEntity(request);
    CustomerEntity customerEntity = customerRepository.saveAndFlush(customerEntityTemp);

    response = customerMapper.toDto(customerEntity);
    return response;
  }
  public CustomerDTO update(CustomerDTO request){
    CustomerDTO response = new CustomerDTO();

    Optional<CustomerEntity> optionalCliente = customerRepository.findByUniqueCode(request.getCodigoUnico());
    CustomerEntity customerEntityTemp = new CustomerEntity();
    if(optionalCliente.isPresent()){
      customerEntityTemp = customerMapper.toEntity(request);
      customerEntityTemp.setId(optionalCliente.get().getId());
      customerEntityTemp.setUniqueCode(optionalCliente.get().getUniqueCode());
    }else{
      throw new ValidationException("Error-403","El cliente no se encuentra en la bd");
    }
    CustomerEntity customerEntity = customerRepository.saveAndFlush(customerEntityTemp);
    response = customerMapper.toDto(customerEntity);
    return response;
  }
  private CustomerDTO validarRequest(CustomerDTO request) {
    CustomerDTO requestValidated = new CustomerDTO();
    String codigoUnico = request.getCodigoUnico();
    if(codigoUnico.isEmpty() || codigoUnico.trim().isEmpty()){
      //lanzarError("M-404","El codigo unico esta vacio o es solo espacios");
    }

    if(request.getTipoDocumento().trim().isEmpty()){
      //lanzarError("M-405", "El tipo de documento no puede ser vacio");
    }
    if(TipoDocumentoEnum.obtenerPorDescripcion(request.getTipoDocumento()) == -1){
      //lanzarError("M-406", "El tipo de documento no tiene un formato valido");
    }

    try {
      String codigoUnicoDesencriptado = request.getCodigoUnico();
      requestValidated = request;
      requestValidated.setCodigoUnico(codigoUnicoDesencriptado);
    }catch (Exception e){
      log.error("Ocurrio un error al desencriptar se asumira que el codigoUnico ya estaba desencriptado");
    }

    return requestValidated;
  }

}
