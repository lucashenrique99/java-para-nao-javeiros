package com.everis.helloworld.dto.mapper;

import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.EditarClienteRequestDTO;
import com.everis.helloworld.dto.NovoClienteRequestDTO;

public class ClientesDTOMapper {

    public static ClienteDTO from(NovoClienteRequestDTO request) {
        return ClienteDTO.builder()
                .nome(request.getNome())
                .cargo(request.getCargo())
                .ativo(Boolean.TRUE)
                .build();
    }

    public static ClienteDTO from(EditarClienteRequestDTO request) {
        return ClienteDTO.builder()
                .id(request.getId())
                .nome(request.getNome())
                .cargo(request.getCargo())
                .ativo(request.getAtivo())
                .build();
    }

}
