package com.javanauta.bffagendadortarefas.business.dto.responses;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioResponse {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoResponse> enderecos;
    private List<TelefoneResponse> telefones;
}
