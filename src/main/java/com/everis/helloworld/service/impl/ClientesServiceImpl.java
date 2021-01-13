package com.everis.helloworld.service.impl;

import com.everis.helloworld.dto.ClienteDTO;
import com.everis.helloworld.dto.DetalhesClienteDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ClientesService;
import com.everis.helloworld.utils.ResponseUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService {

    private static final String CLIENTES_URI = "/clientes";
    private static final String AUTHORIZATION_TOKEN = "gS(zEF5nqL!DJuc=";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private WebTarget webTarget;

    @Autowired
    private ResponseUtilHelper parseHelper;

    @Override
    public DetalhesClienteDTO inserir(ClienteDTO clienteDTO) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN)
                .post(Entity.entity(parseHelper.write(clienteDTO), MediaType.APPLICATION_JSON_TYPE));

        return parseHelper.read(response, DetalhesClienteDTO.class);
    }

    @Override
    public DetalhesClienteDTO editar(ClienteDTO clienteDTO) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(clienteDTO.getId())
                .request(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN)
                .put(Entity.entity(parseHelper.write(clienteDTO), MediaType.APPLICATION_JSON_TYPE));

        return parseHelper.read(response, DetalhesClienteDTO.class);
    }

    @Override
    public DetalhesClienteDTO findById(String id) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN)
                .get();

        return parseHelper.read(response, DetalhesClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> findAll() throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN)
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
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN)
                .get();

        return Arrays.asList(parseHelper.read(response, ClienteDTO[].class));
    }

}
