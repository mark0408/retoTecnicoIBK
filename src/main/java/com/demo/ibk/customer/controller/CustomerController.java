package com.demo.ibk.customer.controller;

import com.demo.ibk.customer.dto.CustomerDTO;
import com.demo.ibk.customer.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

  private final ICustomerService ICustomerService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/{uniqueCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
  })
  @Operation(summary = "Devuelve el listado de todos los customers", tags = {"customer"})
  public ResponseEntity<CustomerDTO> getCustomerByUniqueCode(@PathVariable String uniqueCode){
    return ResponseEntity.ok(ICustomerService.getByUniqueCode(uniqueCode));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
  })
  @Operation(summary = "Crear y guardar en bd el customer", tags = {"customer"})
  public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO request){
    return ResponseEntity.ok(ICustomerService.save(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
  })
  @Operation(summary = "Actualizar y guardar en bd el customer", tags = {"customer"})
  public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO request){
    return ResponseEntity.ok(ICustomerService.update(request));
  }

}
