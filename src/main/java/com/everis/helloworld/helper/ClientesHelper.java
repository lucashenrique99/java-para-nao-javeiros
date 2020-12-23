package com.everis.helloworld.helper;

import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.dto.EditarClienteRequestDTO;
import com.everis.helloworld.dto.NovoClienteRequestDTO;
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
import java.util.Optional;

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
        Optional<DetalhesClienteDTO> dto = clientesService.inserir(ClientesDTOMapper.from(novoClienteRequestDTO));
        if (!dto.isPresent()) {
            throw new ErroApiException("Não foi possível salvar o cliente");
        }
        contasService.inserir(dto.get().getId())
                .orElseThrow(() -> new ErroApiException("Não foi possível criar uma conta para o cliente"));

        return ClienteViewModelMapper.from(dto.get());
    }

    public ClienteViewModel editar(EditarClienteRequestDTO editarClienteRequestDTO) throws ErroApiException {
        Optional<DetalhesClienteDTO> dto = clientesService.editar(ClientesDTOMapper.from(editarClienteRequestDTO));
        if (!dto.isPresent()) {
            throw new ErroApiException("Não foi possível salvar o cliente");
        }

        return ClienteViewModelMapper.from(dto.get());
    }

    public DetalhesClienteViewModel findById(String id) throws ErroApiException {
        Optional<DetalhesClienteDTO> dto = clientesService.findById(id);
        if (!dto.isPresent()) {
            throw new ErroApiException("Cliente não encontrado");
        }

        return DetalhesClienteViewModelMapper.from(dto.get());
    }

    public void excluirPeloId(String id) throws ErroApiException {
        clientesService.excluirPeloId(id);
    }

}
