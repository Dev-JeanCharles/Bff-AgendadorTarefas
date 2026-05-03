package com.javanauta.bffagendadortarefas.business.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneRequest {
    private String numero;
    private String ddd;
}
