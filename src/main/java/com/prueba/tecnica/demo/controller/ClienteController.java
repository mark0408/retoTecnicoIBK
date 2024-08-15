package com.prueba.tecnica.demo.controller;

import com.prueba.tecnica.demo.dto.ClienteCrearResponse;
import com.prueba.tecnica.demo.dto.ClienteListarRequest;
import com.prueba.tecnica.demo.dto.ClienteDTO;
import com.prueba.tecnica.demo.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/obtener", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
  })
  @Operation(summary = "Devuelve el listado de todos los clientes", tags = {"cliente"})
  public ResponseEntity<ClienteDTO> obtenerClientePorCodigoUnico(@Valid @RequestBody ClienteListarRequest request){
    return ResponseEntity.ok(clienteService.obtenerClientePorCodigoUnico(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/obtener/encriptado", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
  })
  @Operation(summary = "Devuelve el listado de todos los clientes", tags = {"cliente"})
  public ResponseEntity<ClienteDTO> obtenerClientePorCodigoUnicoEncriptado(@Valid @RequestBody ClienteListarRequest request){
    return ResponseEntity.ok(clienteService.obtenerClientePorCodigoUnicoEncriptado(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
  })
  @Operation(summary = "Crear y guardar en bd el cliente", tags = {"cliente"})
  public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO request){
    return ResponseEntity.ok(clienteService.crearCliente(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
  })
  @Operation(summary = "Actualizar y guardar en bd el cliente", tags = {"cliente"})
  public ResponseEntity<ClienteDTO> actualizarCliente(@Valid @RequestBody ClienteDTO request){
    return ResponseEntity.ok(clienteService.actualizarCliente(request));
  }

}
