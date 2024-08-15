package com.prueba.tecnica.demo.service;


import com.prueba.tecnica.demo.domain.Cliente;
import com.prueba.tecnica.demo.dto.ClienteListarRequest;
import com.prueba.tecnica.demo.dto.ClienteDTO;
import com.prueba.tecnica.demo.exception.ValidationException;
import com.prueba.tecnica.demo.mapper.ClienteMapper;
import com.prueba.tecnica.demo.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;
  @Autowired
  private ClienteMapper clienteMapper;

  public ClienteDTO obtenerClientePorCodigoUnico(ClienteListarRequest request){
    log.info("Entro al metodo obtenerClientePorCodigoUnico");
    ClienteDTO response = new ClienteDTO();
    Optional<Cliente> cliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    if(cliente.isPresent()){
      response = clienteMapper.clienteToClienteDto(cliente.get());
    }else{
      log.error("Error no se encuentro cliente");
      lanzarError("M-401","Error no se encuentra Cliente");
    }
    log.info("Salio del metodo obtenerClientePorCodigoUnico");
    return response;
  }
  public ClienteDTO obtenerClientePorCodigoUnicoEncriptado(ClienteListarRequest request){
    log.info("Entro al metodo obtenerClientePorCodigoUnico");
    ClienteDTO response = new ClienteDTO();
   // String tipoCambio = CambioUtils.crearTipoCambio(request.getMonedaOrigen(),request.getMonedaDestino());
   // Optional<Cliente> optionalCambioMoneda = clienteRepository.findByTipoCambio(tipoCambio);
   // if(optionalCambioMoneda.isPresent()){
   //   Double valorTipoCambio = optionalCambioMoneda.get().getValorTipoCambio();
   //   Double montoFinal = obtenerMontoFinal(valorTipoCambio,request.getMonto());
   //   response = crearCambioMonedaResponse(request,montoFinal,valorTipoCambio);
   // }else{
      log.error("Error no se encuentra cambio de Moneda");
      lanzarError("M-401","Error no se encuentra cambio de Moneda");
   // }
    log.info("Salio del metodo obtenerLineaMovilYOfertaPorCliente");
    return response;
  }

  private ClienteDTO mapperClienteDTO(String codigoUnico){
    ClienteDTO response = new ClienteDTO();
    response.setNumeroDocumento(codigoUnico);
    return response;
  }

  public ClienteDTO crearCliente(ClienteDTO request){
    ClienteDTO response = new ClienteDTO();
    // crear o modificar este metodo para que valide si el codigo unico viene encriptado o no, si viniera encirptado desencriptarlo y setearlo en el objeto
    request = validarRequest(request);

    Optional<Cliente> optionalCliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    if(optionalCliente.isPresent()){
      log.error("Ya existe un cliente con este valorUnico");
      lanzarError("M-402","Ya existe un valor unico para este Cliente");
    }
    Cliente clienteTemp = clienteMapper.clienteDtoToCliente(request);
    Cliente cliente = clienteRepository.saveAndFlush(clienteTemp);

    response = clienteMapper.clienteToClienteDto(cliente);
    return response;
  }
  public ClienteDTO actualizarCliente(ClienteDTO request){
    ClienteDTO response = new ClienteDTO();

    request = validarRequest(request);

    Optional<Cliente> optionalCliente = clienteRepository.findByCodigoUnico(request.getCodigoUnico());
    Cliente clienteTemp = new Cliente();
    if(optionalCliente.isPresent()){
      //Actualizar
      clienteTemp.setCodigoUnico(request.getCodigoUnico());
      clienteTemp.setNombres(request.getNombres());

    }else{
      log.error("El cliente no se encuentra en la bd");
      lanzarError("M-403","El cliente no se encuentra en la bd");
    }
    Cliente cliente = clienteRepository.saveAndFlush(clienteTemp);
    response = mapperClienteDTO(cliente.getCodigoUnico());
    return response;
  }
  private ClienteDTO validarRequest(ClienteDTO request) {
    ClienteDTO requestValidated = new ClienteDTO();
    // Valida que el cliente sea valido al tener un codigoUnico
    String codigoUnico = request.getCodigoUnico();
    if(codigoUnico.isEmpty() || codigoUnico.trim().isEmpty()){
      lanzarError("M-404","El codigo unico esta vacio o es solo espacios");
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
