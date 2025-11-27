package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.responses.TarefasResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void enviaEmail(TarefasResponse dto) {
        client.enviarEmail(dto);
    }
}
