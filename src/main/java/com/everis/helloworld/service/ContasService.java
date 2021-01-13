package com.everis.helloworld.service;

import com.everis.helloworld.dto.ContaDTO;
import com.everis.helloworld.exception.ErroApiException;

import java.util.List;

public interface ContasService {

    List<ContaDTO> findByClienteId(String id) throws ErroApiException;

    ContaDTO inserir(String clienteId) throws ErroApiException;

}
