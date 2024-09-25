package com.ebr.proposta_app.service;

import com.ebr.proposta_app.dto.PropostaDTO;
import com.ebr.proposta_app.entity.Proposta;
import com.ebr.proposta_app.mapper.PropostaMapper;
import com.ebr.proposta_app.repository.PropostaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;
    private final NotificacaoRabbitService notificacaoService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    @Transactional
    public PropostaDTO salvar(PropostaDTO dto) {
        Proposta proposta = PropostaMapper.INSTANCE.converterDtoParaProposta(dto);
        repository.save(proposta);
        notificarRabbitMq(proposta);
        notificacaoService.notificar(proposta, exchange);
        return PropostaMapper.INSTANCE.converterEntidadeParaDto(proposta);
    }

    public List<PropostaDTO> obterPropostas() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }

    public void notificarRabbitMq(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            repository.save(proposta);
        }
    }

}
