package com.demo.ibk.customer.entity;

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
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String names;
  @Column
  private String surnames;
  @Column(nullable = false)
  private Integer typeDocument;
  @Column(nullable = false)
  private String numberDocument;
  @Column(nullable = false)
  private String uniqueCode;


}
