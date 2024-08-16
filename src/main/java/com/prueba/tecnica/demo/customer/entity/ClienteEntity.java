package com.prueba.tecnica.demo.customer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nombres;
  @Column(nullable = false)
  private String apellidos;
  @Column(nullable = false)
  private Integer tipoDocumento;
  @Column(nullable = false)
  private String numeroDocumento;
  @Column(nullable = false)
  private String codigoUnico;


}
