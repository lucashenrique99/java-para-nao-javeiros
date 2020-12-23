package com.everis.helloworld.utils;

import com.everis.helloworld.exception.ErroApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;

@Component
public class ResponseUtilHelper {

    @Autowired
    private ObjectMapper mapper;

    public <T> T read(Response response, Class<T> aClass) throws ErroApiException {
        final String body = response.readEntity(String.class);
        try {
            if (isSucesso(response)) {
                return mapper.readValue(body, aClass);
            } else {
                throw new ErroApiException(body);
            }
        } catch (IOException e) {
            throw new ErroApiException(e.getMessage());
        }
    }

    public String write(Object object) throws ErroApiException {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new ErroApiException(e.getMessage());
        }
    }

    public boolean isSucesso(Response response) {
        return response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL);
    }

}
