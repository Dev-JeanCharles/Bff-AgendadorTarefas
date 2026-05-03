package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.requests.TarefasResquest;
import com.javanauta.bffagendadortarefas.business.dto.responses.TarefasResponse;
import com.javanauta.bffagendadortarefas.infrastructure.entity.enums.StatusNotificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasResponse criarTarefa(@RequestBody TarefasResquest dto,
                                @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasResponse> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasResponse> buscaTarefaPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                            @RequestParam("id") String id,
                                            @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasResponse atualizaTarefas(@RequestBody TarefasResquest dto,
                                    @RequestParam("id") String id,
                                    @RequestHeader("Authorization") String token);
}
