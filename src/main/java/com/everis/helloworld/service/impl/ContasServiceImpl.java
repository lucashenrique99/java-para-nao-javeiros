package com.everis.helloworld.service.impl;

import com.everis.helloworld.utils.ResponseUtilHelper;
import com.everis.helloworld.dto.ContaDTO;
import com.everis.helloworld.exception.ErroApiException;
import com.everis.helloworld.service.ContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ContasServiceImpl implements ContasService {

    private static final String CLIENTES_URI = "/clientes";
    private static final String CONTAS_URI = "/contas";

    @Autowired
    private WebTarget webTarget;

    @Autowired
    private ResponseUtilHelper parseHelper;

    @Override
    public Optional<ContaDTO> inserir(String clienteId) throws ErroApiException {
        Response response = webTarget.path(CLIENTES_URI)
                .path(clienteId)
                .path(CONTAS_URI)
                .request(MediaType.APPLICATION_JSON)
                .method("POST");

        return Optional.ofNullable(parseHelper.read(response, ContaDTO.class));
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
