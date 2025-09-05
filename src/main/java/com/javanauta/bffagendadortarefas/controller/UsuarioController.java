package com.javanauta.bffagendadortarefas.controller;


import com.javanauta.bffagendadortarefas.business.UsuarioService;
import com.javanauta.bffagendadortarefas.business.dto.requests.EnderecoRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.LoginRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.TelefoneRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.UsuarioRequest;
import com.javanauta.bffagendadortarefas.business.dto.responses.EnderecoResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.TelefoneResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponse> salvaUsuario(@RequestBody UsuarioRequest usuarioRequest) {
     return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email",
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                               @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por Id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de Usuários",
            description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponse> atualizaDadosUsuario(@RequestBody UsuarioRequest usuarioRequest,
                                                               @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioRequest));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Endereço de Usuários",
            description = "Atualizar endereços de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoResponse> atualizaEndereco(@RequestBody EnderecoRequest enderecoRequest,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoRequest, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Telefone de Usuários",
            description = "Atualizar telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneResponse> atualizaTelefone(@RequestBody TelefoneRequest telefoneRequest,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneRequest, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuários",
            description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoResponse> cadastraEndereco(@RequestBody EnderecoRequest enderecoRequest,
                                                             @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoRequest));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuários",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneResponse> cadastraTelefone(@RequestBody TelefoneRequest telefoneRequest,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneRequest));
    }
}
