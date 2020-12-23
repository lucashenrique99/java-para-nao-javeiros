package com.everis.helloworld.service;

import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.exception.ErroApiException;

import java.util.List;
import java.util.Optional;

public interface ClientesService {

    Optional<DetalhesClienteDTO> inserir(ClienteDTO cliente) throws ErroApiException;
    Optional<DetalhesClienteDTO> editar(ClienteDTO cliente) throws ErroApiException;

    Optional<DetalhesClienteDTO> findById(String id) throws ErroApiException;

    List<ClienteDTO> findAll() throws ErroApiException;

    List<ClienteDTO> findAll(String nome, Integer pagina, Integer limite) throws ErroApiException;

    void excluirPeloId(String id) throws ErroApiException;

}
