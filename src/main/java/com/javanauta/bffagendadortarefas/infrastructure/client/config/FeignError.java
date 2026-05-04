package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.*;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;

public class FeignError implements ErrorDecoder {

    private static final String ERROR_PREFIX = "Erro: ";

    private static final Map<Integer, Function<String, Exception>> EXCEPTION_MAP = Map.of(
            400, IllegalArgumentException::new,
            401, UnauthorizedException::new,
            403, ResourceNotFoundException::new,
            409, ConflictException::new
    );

    @Override
    public Exception decode(String methodKey, Response response) {

        String mensagem = buildMessage(response);

        return EXCEPTION_MAP
                .getOrDefault(response.status(), this::defaultException)
                .apply(mensagem);
    }

    private String buildMessage(Response response) {
        return ERROR_PREFIX + extractBody(response);
    }

    private String extractBody(Response response) {
        if (response.body() == null) {
            return "";
        }

        try {
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "Erro ao ler resposta do serviço externo";
        }
    }

    private Exception defaultException(String message) {
        return new BusinessException("Erro externo: " + message);
    }
}
