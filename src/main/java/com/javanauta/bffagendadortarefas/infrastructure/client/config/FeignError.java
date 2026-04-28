package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            String body = response.body() != null ?
                    new String(response.body().asInputStream().readAllBytes()) : "";

            System.out.println("Erro Feign: " + body);

        } catch (Exception ignored) {}

        return new BusinessException("Erro ao chamar serviço externo");
    }
}
