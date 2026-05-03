package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.requests.EnderecoRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.LoginRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.TelefoneRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.UsuarioRequest;
import com.javanauta.bffagendadortarefas.business.dto.responses.EnderecoResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.TelefoneResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.UsuarioResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.ViaCepDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioClient client;

    public UsuarioResponse salvaUsuario(UsuarioRequest usuarioRequest) {
        return client.salvaUsuario(usuarioRequest);
    }

    public UsuarioResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public String loginUsuario(LoginRequest dto) {
        return client.login(dto);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioResponse atualizaDadosUsuario(String token, UsuarioRequest usuarioRequest) {
        return client.atualizaDadosUsuario(usuarioRequest, token);
    }

    public EnderecoResponse atualizaEndereco(Long idEndereco, EnderecoRequest enderecoRequest, String token) {
        return client.atualizaEndereco(enderecoRequest, idEndereco, token);
    }

    public TelefoneResponse atualizaTelefone(Long idTelefone, TelefoneRequest telefoneRequest, String token) {
        return client.atualizaTelefone(telefoneRequest, idTelefone, token);
    }

    public EnderecoResponse cadastraEndereco(String token, EnderecoRequest dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneResponse cadastraTelefone(String token, TelefoneRequest dto) {
        return client.cadastraTelefone(dto, token);
    }

    public ViaCepDTOResponse buscaEnderecoPorCep(String cep) {
        return client.buscaDadosEndereco(cep);
    }
}
