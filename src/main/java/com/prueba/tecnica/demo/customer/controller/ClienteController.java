package com.prueba.tecnica.demo.customer.controller;

import com.prueba.tecnica.demo.customer.dto.ClienteListarRequest;
import com.prueba.tecnica.demo.customer.dto.ClienteDTO;
import com.prueba.tecnica.demo.customer.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
  @GetMapping(value = "/obtener/encriptado/{codigoUnicoEncriptado}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
  })
  @Operation(summary = "Devuelve el listado de todos los clientes", tags = {"cliente"})
  public ResponseEntity<ClienteDTO> obtenerClientePorCodigoUnicoEncriptado(@PathVariable String codigoUnicoEncriptado){
    return ResponseEntity.ok(clienteService.obtenerClientePorCodigoUnicoEncriptado(codigoUnicoEncriptado));
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
