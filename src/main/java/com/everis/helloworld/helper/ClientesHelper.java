package com.everis.helloworld.helper;

import com.everis.helloworld.dto.*;
import com.everis.helloworld.dto.mapper.ClientesDTOMapper;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ClientesService;
import com.everis.helloworld.service.ContasService;
import com.everis.helloworld.viewmodel.ClienteViewModel;
import com.everis.helloworld.viewmodel.DetalhesClienteViewModel;
import com.everis.helloworld.viewmodel.mapper.ClienteViewModelMapper;
import com.everis.helloworld.viewmodel.mapper.DetalhesClienteViewModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientesHelper {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private ContasService contasService;

    public List<ClienteViewModel> findAllClientes() throws ErroApiException {
        List<ClienteDTO> list = clientesService.findAll();
        return ClienteViewModelMapper.fromList(list);
    }

    public List<ClienteViewModel> findAllClientes(String nome, Integer pagina, Integer limite) throws ErroApiException {
        List<ClienteDTO> list = clientesService.findAll(nome, pagina, limite);
        return ClienteViewModelMapper.fromList(list);
    }

    public ClienteViewModel inserir(NovoClienteRequestDTO novoClienteRequestDTO) throws ErroApiException {
        DetalhesClienteDTO clienteDTO = clientesService.inserir(ClientesDTOMapper.from(novoClienteRequestDTO));
        if (clienteDTO == null) {
            throw new ErroApiException("Não foi possível salvar o cliente");
        }

        ContaDTO contaDTO = contasService.inserir(clienteDTO.getId());
        if (contaDTO == null) {
            throw new ErroApiException("Não foi possível criar uma conta para o cliente");
        }

        return ClienteViewModelMapper.from(clienteDTO);
    }

    public ClienteViewModel editar(EditarClienteRequestDTO editarClienteRequestDTO) throws ErroApiException {
        DetalhesClienteDTO dto = clientesService.editar(ClientesDTOMapper.from(editarClienteRequestDTO));
        if (dto == null) {
            throw new ErroApiException("Não foi possível salvar o cliente");
        }

        return ClienteViewModelMapper.from(dto);
    }

    public DetalhesClienteViewModel findById(String id) throws ErroApiException {
        DetalhesClienteDTO dto = clientesService.findById(id);
        if (dto == null) {
            throw new ErroApiException("Cliente não encontrado");
        }

        return DetalhesClienteViewModelMapper.from(dto);
    }

}
