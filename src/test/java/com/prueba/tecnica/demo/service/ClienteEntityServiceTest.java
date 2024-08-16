package com.prueba.tecnica.demo.service;

import com.prueba.tecnica.demo.customer.dto.ClienteDTO;
import com.prueba.tecnica.demo.customer.dto.ClienteListarRequest;
import com.prueba.tecnica.demo.customer.entity.ClienteEntity;
import com.prueba.tecnica.demo.customer.service.ClienteService;
import com.prueba.tecnica.demo.commons.exception.ValidationException;
import com.prueba.tecnica.demo.customer.mapper.ClienteMapper;
import com.prueba.tecnica.demo.customer.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClienteEntityServiceTest {

    @InjectMocks
    ClienteService clienteService;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ClienteMapper clienteMapper;
    //ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtenerClientePorCodigoUnico(){
        ClienteEntity clienteEntityMock = new ClienteEntity(1L,"Marko","Apellido",1,"75371362","1");
        ClienteDTO valorEsperado = new ClienteDTO(1L,"1","Marko","Apellido","DNI","75371362");
        when(clienteRepository.findByCodigoUnico("1")).thenReturn(Optional.of(clienteEntityMock));
        when(clienteMapper.clienteToClienteDto(clienteEntityMock)).thenReturn(valorEsperado);

        ClienteDTO valorActual = clienteService.obtenerClientePorCodigoUnico(new ClienteListarRequest("1"));

        assertEquals(valorEsperado, valorActual);
    }


    @Test
    public void crearCliente(){
        ClienteEntity clienteEntityMock = new ClienteEntity(1L,"Marko","Apellido",1,"75371362","1");
        ClienteDTO valorEsperado = new ClienteDTO(1L,"1","Marko","Apellido","DNI","75371362");
        when(clienteRepository.findByCodigoUnico("1")).thenReturn(Optional.empty());
        when(clienteRepository.saveAndFlush(clienteEntityMock)).thenReturn(clienteEntityMock);
        when(clienteMapper.clienteToClienteDto(clienteEntityMock)).thenReturn(valorEsperado);
        when(clienteMapper.clienteDtoToCliente(valorEsperado)).thenReturn(clienteEntityMock);

        ClienteDTO valorActual = clienteService.crearCliente(valorEsperado);

        assertEquals(valorEsperado, valorActual);
    }

    @Test(expected = ValidationException.class)
    @DisplayName("crear cliente lanzando el error de que ya existe")
    public void crearClienteErrorYaExiste(){
        ClienteEntity clienteEntityMock = new ClienteEntity(1L,"Marko","Apellido",1,"75371362","1");
        ClienteDTO valorEsperado = new ClienteDTO(1L,"1","Marko","Apellido","DNI","75371362");
        when(clienteRepository.findByCodigoUnico("1")).thenReturn(Optional.of(clienteEntityMock));

        ClienteDTO valorActual = clienteService.crearCliente(valorEsperado);

        assertEquals(valorEsperado, valorActual);
    }



}
