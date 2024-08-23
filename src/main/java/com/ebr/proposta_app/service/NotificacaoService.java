package com.ebr.proposta_app.service;

import com.ebr.proposta_app.dto.PropostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(PropostaDTO proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
