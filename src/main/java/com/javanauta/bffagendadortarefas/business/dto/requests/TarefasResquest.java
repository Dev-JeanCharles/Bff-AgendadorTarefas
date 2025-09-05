package com.javanauta.bffagendadortarefas.business.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bffagendadortarefas.infrastructure.entity.enums.StatusNotificacao;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TarefasResquest {
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}
