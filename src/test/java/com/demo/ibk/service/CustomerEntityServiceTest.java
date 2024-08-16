package com.demo.ibk.service;

import com.demo.ibk.commons.util.encryption.EncryptionContext;
import com.demo.ibk.customer.dto.CustomerDTO;
import com.demo.ibk.customer.entity.CustomerEntity;
import com.demo.ibk.customer.service.CostumerService;
import com.demo.ibk.commons.exception.ValidationException;
import com.demo.ibk.customer.mapper.CustomerMapper;
import com.demo.ibk.customer.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerEntityServiceTest {

    @InjectMocks
    CostumerService clienteService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    EncryptionContext encryptionContext;
    @Mock
    CustomerMapper customerMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void crearCliente(){
        // Arrange: Crea el mock de la entidad y el valor esperado del DTO
        CustomerEntity customerEntityMock = new CustomerEntity(1L,"Marko","Apellido",1,"75371362","1");
        CustomerDTO valorEsperado = new CustomerDTO("1","Marko","Apellido","DNI","75371362");

        // Define el comportamiento de los mocks
        when(customerRepository.findByUniqueCode("1")).thenReturn(Optional.empty());
        when(customerRepository.saveAndFlush(customerEntityMock)).thenReturn(customerEntityMock);
        when(customerMapper.toDto(customerEntityMock)).thenReturn(valorEsperado);
        when(customerMapper.toEntity(valorEsperado)).thenReturn(customerEntityMock);
        when(encryptionContext.encrypt("1")).thenReturn("1");

        // Act: Llama al servicio que estás probando
        CustomerDTO valorActual = clienteService.save(valorEsperado);

        // Assert: Verifica que el valor actual es igual al esperado
        assertEquals(valorEsperado, valorActual);
    }


    @Test
    public void obtenerClientePorCodigoUnico() {
        // Arrange: Crea el mock de la entidad y el valor esperado del DTO
        CustomerEntity customerEntityMock = new CustomerEntity(1L,"Marko","Apellido",1,"75371362","1");
        CustomerDTO valorEsperado = new CustomerDTO("1","Marko","Apellido","DNI","75371362");

        // Define el comportamiento de los mocks
        when(customerRepository.findByUniqueCode("1")).thenReturn(Optional.of(customerEntityMock));
        when(customerMapper.toDto(customerEntityMock)).thenReturn(valorEsperado);

        // Act: Llama al servicio que estás probando
        CustomerDTO valorActual = clienteService.getByUniqueCode("1");

        // Assert: Verifica que el valor actual es igual al esperado
        assertEquals(valorEsperado, valorActual);
    }


}
