package com.prueba.tecnica.demo.customer.service;


import com.prueba.tecnica.demo.customer.entity.ClienteEntity;
import com.prueba.tecnica.demo.commons.enums.TipoDocumentoEnum;
import com.prueba.tecnica.demo.customer.dto.ClienteListarRequest;
import com.prueba.tecnica.demo.customer.dto.ClienteDTO;
import com.prueba.tecnica.demo.commons.exception.ValidationException;
import com.prueba.tecnica.demo.customer.mapper.ClienteMapper;
import com.prueba.tecnica.demo.customer.repository.ClienteRepository;
import com.prueba.tecnica.demo.commons.util.EncriptarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@Transactional
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;
  @Autowired
  ClienteMapper clienteMapper;

  public ClienteDTO obtenerClientePorCodigoUnico(ClienteListarRequest request){
    ClienteDTO response = new ClienteDTO();
    Optional<ClienteEntity> cliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    if(cliente.isPresent()){
      response = clienteMapper.clienteToClienteDto(cliente.get());
    }else{
      lanzarError("M-401","Error no se encuentra Cliente");
    }
    return response;
  }
  public ClienteDTO obtenerClientePorCodigoUnicoEncriptado(String request){
    log.info("Entro al metodo obtenerClientePorCodigoUnicoEncriptado");
    ClienteDTO response = new ClienteDTO();
    String codigoUnicoDesencriptado = EncriptarUtils.Desencriptar(request);
    Optional<ClienteEntity> cliente = clienteRepository.findByCodigoUnico(codigoUnicoDesencriptado);
    if(cliente.isPresent()){
      response = clienteMapper.clienteToClienteDto(cliente.get());
    }else{
      log.error("Error no se encuentro cliente");
      lanzarError("M-401","Error no se encuentra Cliente");
    }
    log.info("Salio del metodo obtenerClientePorCodigoUnicoEncriptado");
    return response;
  }

  public ClienteDTO crearCliente(ClienteDTO request){
    ClienteDTO response = new ClienteDTO();
    request = validarRequest(request);

    Optional<ClienteEntity> optionalCliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    if(optionalCliente.isPresent()){
      throw new ValidationException("M-402","Ya existe un valor unico para este Cliente");
    }
    ClienteEntity clienteEntityTemp = clienteMapper.clienteDtoToCliente(request);
    ClienteEntity clienteEntity = clienteRepository.saveAndFlush(clienteEntityTemp);

    response = clienteMapper.clienteToClienteDto(clienteEntity);
    return response;
  }
  public ClienteDTO actualizarCliente(ClienteDTO request){
    ClienteDTO response = new ClienteDTO();

    request = validarRequest(request);

    Optional<ClienteEntity> optionalCliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    ClienteEntity clienteEntityTemp = new ClienteEntity();
    if(optionalCliente.isPresent()){
      clienteEntityTemp = clienteMapper.clienteDtoToCliente(request);
      clienteEntityTemp.setId(optionalCliente.get().getId());
      clienteEntityTemp.setCodigoUnico(optionalCliente.get().getCodigoUnico());
    }else{
      lanzarError("M-403","El cliente no se encuentra en la bd");
    }
    ClienteEntity clienteEntity = clienteRepository.saveAndFlush(clienteEntityTemp);
    response = clienteMapper.clienteToClienteDto(clienteEntity);
    return response;
  }
  private ClienteDTO validarRequest(ClienteDTO request) {
    ClienteDTO requestValidated = new ClienteDTO();
    // Valida que el cliente sea valido al tener un codigoUnico
    String codigoUnico = request.getCodigoUnico();
    if(codigoUnico.isEmpty() || codigoUnico.trim().isEmpty()){
      lanzarError("M-404","El codigo unico esta vacio o es solo espacios");
    }

    // Validar el campo tipo documento
    if(request.getTipoDocumento().trim().isEmpty()){
      lanzarError("M-405", "El tipo de documento no puede ser vacio");
    }
    if(TipoDocumentoEnum.obtenerPorDescripcion(request.getTipoDocumento()) == -1){
      lanzarError("M-406", "El tipo de documento no tiene un formato valido");
    }


    //Validar si codigo unico esta encriptado o no
    try {
      String codigoUnicoDesencriptado = request.getCodigoUnico();
      requestValidated = request;
      requestValidated.setCodigoUnico(codigoUnicoDesencriptado);
    }catch (Exception e){
      log.error("Ocurrio un error al desencriptar se asumira que el codigoUnico ya estaba desencriptado");
    }

    return requestValidated;
  }

  private void lanzarError(String code,String errorMessage){
    throw new ValidationException(code,errorMessage);
  }
}
