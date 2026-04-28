package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.requests.LoginRequest;
import com.javanauta.bffagendadortarefas.business.dto.responses.TarefasResponse;
import com.javanauta.bffagendadortarefas.infrastructure.entity.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {

        try {
            String token = login(converterParaRequestDTO());
            log.info("[CRON-SERVICE] Iniciando processo de busca de tarefas - scheduler ativo");
            LocalDateTime horaAtual = LocalDateTime.now();
            LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

            List<TarefasResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);

            log.info("[CRON-SERVICE] Tarefas encontradas: {}", listaTarefas);
            listaTarefas.forEach(tarefa -> {emailService.enviaEmail(tarefa);
                log.info("[CRON-SERVICE] Email enviado para o usuário: {}", tarefa.getEmail());
                tarefasService.alteraStatus(StatusNotificacao.NOTIFICADO, tarefa.getId(), token);});

            log.info("[CRON-SERVICE] Finalizada a busca e notificação de tarefas");
        }catch (Exception e){
            log.error("[CRON-SERVICE] Erro ao executar scheduler", e);
        }
    }

    public String login(LoginRequest request) {
        return usuarioService.loginUsuario(request);
    }

    public  LoginRequest converterParaRequestDTO() {
        return LoginRequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
