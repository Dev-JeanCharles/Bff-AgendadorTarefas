package com.javanauta.bffagendadortarefas.business.dto.requests;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoRequest> enderecos;
    private List<TelefoneRequest> telefones;
}
