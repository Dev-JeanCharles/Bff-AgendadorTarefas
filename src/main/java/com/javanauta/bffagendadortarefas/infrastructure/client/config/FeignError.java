package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.*;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        String mensagem = mensagemErro(response);

        return switch (response.status()) {
            case 400 -> new IllegalArgumentException("Erro: " + mensagem);
            case 401 -> new UnauthorizedException("Erro: " + mensagem);
            case 403 -> new ResourceNotFoundException("Erro: " + mensagem);
            case 409 -> new ConflictException("Erro: " + mensagem);
            default -> new BusinessException("Erro externo: " + mensagem);
        };
    }

    private String mensagemErro(Response response) {
        try {
            return response.body() != null
                    ? new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8)
                    : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
