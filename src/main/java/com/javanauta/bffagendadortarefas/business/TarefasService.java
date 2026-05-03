package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.requests.TarefasResquest;
import com.javanauta.bffagendadortarefas.business.dto.responses.TarefasResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.javanauta.bffagendadortarefas.infrastructure.entity.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;

    public TarefasResponse gravarTarefas(String token, TarefasResquest dto) {
        return client.criarTarefa(dto, token);
    }

    public List<TarefasResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return client.buscaListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasResponse> buscaTarefaPorEmail(String token) {
        return client.buscaTarefaPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasResponse alteraStatus(StatusNotificacao status, String id, String token) {
        return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasResponse atualizaTarefas(TarefasResquest dto, String id, String token) {
        return client.atualizaTarefas(dto, id, token);
    }
}
