package com.everis.helloworld.service;

import com.everis.helloworld.dto.ContaDTO;
import com.everis.helloworld.exception.ErroApiException;

import java.util.List;
import java.util.Optional;

public interface ContasService {

    List<ContaDTO> findByClienteId(String id) throws ErroApiException;

    Optional<ContaDTO> inserir(String clienteId) throws ErroApiException;

}
