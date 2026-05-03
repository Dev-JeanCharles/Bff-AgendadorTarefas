package com.javanauta.bffagendadortarefas.business.dto.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    private String email;
    private String senha;
}
