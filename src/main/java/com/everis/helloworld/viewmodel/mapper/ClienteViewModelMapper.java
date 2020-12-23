package com.everis.helloworld.viewmodel.mapper;

import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.viewmodel.ClienteViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteViewModelMapper {

    public static ClienteViewModel from(ClienteDTO dto) {
        return ClienteViewModel.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cargo(dto.getCargo())
                .build();
    }

    public static ClienteViewModel from(DetalhesClienteDTO dto) {
        return ClienteViewModel.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cargo(dto.getCargo())
                .build();
    }

    public static List<ClienteViewModel> fromList(List<ClienteDTO> list) {
        return list.stream()
                .map(ClienteViewModelMapper::from)
                .collect(Collectors.toList());
    }

}
