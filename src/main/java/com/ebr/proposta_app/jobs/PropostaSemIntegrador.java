package com.ebr.proposta_app.jobs;

import com.ebr.proposta_app.entity.Proposta;
import com.ebr.proposta_app.repository.PropostaRepository;
import com.ebr.proposta_app.service.NotificacaoRabbitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PropostaSemIntegrador {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitService notificacaoRabbitService;

    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegrador.class);

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao() {
        propostaRepository.findAllByIntegradaIsFalse()
                .forEach(proposta -> {
                    try {
                        notificacaoRabbitService.notificar(proposta, exchange);
                        atualizarProposta(proposta);
                    } catch (RuntimeException r) {
                        System.out.println("Erro ao tentar reintegrar proposta");
                        logger.error(r.getMessage());
                    }
                });
    }

    private void atualizarProposta(Proposta proposta) {
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }

}
