package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.requests.EnderecoRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.LoginRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.TelefoneRequest;
import com.javanauta.bffagendadortarefas.business.dto.requests.UsuarioRequest;
import com.javanauta.bffagendadortarefas.business.dto.responses.EnderecoResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.TelefoneResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.UsuarioResponse;
import com.javanauta.bffagendadortarefas.business.dto.responses.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

 @PostMapping
 UsuarioResponse salvaUsuario(@RequestBody UsuarioRequest usuarioRequest);

 @PostMapping("/login")
 String login(@RequestBody LoginRequest dto);

 @GetMapping
 UsuarioResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                      @RequestHeader("Authorization") String token);

 @DeleteMapping("/{email}")
 void deletaUsuarioPorEmail(@PathVariable String email,
                            @RequestHeader("Authorization") String token);

 @PutMapping
 UsuarioResponse atualizaDadosUsuario(@RequestBody UsuarioRequest usuarioRequest,
                                      @RequestHeader("Authorization") String token);

 @PutMapping("/endereco")
 EnderecoResponse atualizaEndereco(@RequestBody EnderecoRequest enderecoRequest,
                                   @RequestParam("id") Long id,
                                   @RequestHeader("Authorization") String token);

 @PutMapping("/telefone")
 TelefoneResponse atualizaTelefone(@RequestBody TelefoneRequest telefoneRequest,
                                   @RequestParam("id") Long id,
                                   @RequestHeader("Authorization") String token);

 @PostMapping("/endereco")
 EnderecoResponse cadastraEndereco(@RequestBody EnderecoRequest enderecoRequest,
                                   @RequestHeader("Authorization") String token);

 @PostMapping("/telefone")
 TelefoneResponse cadastraTelefone(@RequestBody TelefoneRequest telefoneRequest,
                                   @RequestHeader("Authorization") String token);

 @GetMapping("/endereco/{cep}")
  ViaCepDTOResponse buscaDadosEndereco(@PathVariable("cep") String cep);
}
