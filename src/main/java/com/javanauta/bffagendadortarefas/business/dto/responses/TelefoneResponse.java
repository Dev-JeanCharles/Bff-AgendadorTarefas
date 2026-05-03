package com.javanauta.bffagendadortarefas.business.dto.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneResponse {
    private Long id;
    private String numero;
    private String ddd;
}
