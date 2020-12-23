package com.everis.helloworld.service.impl;

import com.everis.helloworld.utils.ResponseUtilHelper;
import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesServiceImpl implements ClientesService {

    private static final String CLIENTES_URI = "/clientes";

    @Autowired
    private WebTarget webTarget;

    @Autowired
    private ResponseUtilHelper parseHelper;

    @Override
    public Optional<DetalhesClienteDTO> inserir(ClienteDTO clienteDTO) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(parseHelper.write(clienteDTO), MediaType.APPLICATION_JSON_TYPE));

        return Optional.ofNullable(parseHelper.read(response, DetalhesClienteDTO.class));
    }
    @Override
    public Optional<DetalhesClienteDTO> editar(ClienteDTO clienteDTO) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(clienteDTO.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(parseHelper.write(clienteDTO), MediaType.APPLICATION_JSON_TYPE));

        return Optional.ofNullable(parseHelper.read(response, DetalhesClienteDTO.class));
    }

    @Override
    public Optional<DetalhesClienteDTO> findById(String id) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return Optional.ofNullable(parseHelper.read(response, DetalhesClienteDTO.class));
    }

    @Override
    public List<ClienteDTO> findAll() throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return Arrays.asList(parseHelper.read(response, ClienteDTO[].class));
    }

    @Override
    public List<ClienteDTO> findAll(String nome, Integer pagina, Integer limite) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .queryParam("filter", nome)
                .queryParam("sortBy", "nome")
                .queryParam("order", "asc")
                .queryParam("page", pagina)
                .queryParam("limit", limite)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return Arrays.asList(parseHelper.read(response, ClienteDTO[].class));
    }

    @Override
    public void excluirPeloId(String id) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        if (!parseHelper.isSucesso(response)) {
            throw new ErroApiException("Erro durante a requisição. HTTP " + response.getStatus());
        }

        response.close();
    }
}
