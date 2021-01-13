package com.everis.helloworld.service.impl;

import com.everis.helloworld.dto.ContaDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ContasService;
import com.everis.helloworld.utils.ResponseUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Service
public class ContasServiceImpl implements ContasService {

    private static final String CLIENTES_URI = "/clientes";
    private static final String CONTAS_URI = "/contas";

    @Autowired
    private WebTarget webTarget;

    @Autowired
    private ResponseUtilHelper parseHelper;

    @Override
    public ContaDTO inserir(String clienteId) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(clienteId)
                .path(CONTAS_URI)
                .request(MediaType.APPLICATION_JSON)
                .method("POST");

        return parseHelper.read(response, ContaDTO.class);
    }

    @Override
    public List<ContaDTO> findByClienteId(String id) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(id)
                .path(CONTAS_URI)
                .request(MediaType.APPLICATION_JSON)
                .get();

        return Arrays.asList(parseHelper.read(response, ContaDTO[].class));
    }
}
