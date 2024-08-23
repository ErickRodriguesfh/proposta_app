package com.ebr.proposta_app.service;

import com.ebr.proposta_app.dto.PropostaDTO;
import com.ebr.proposta_app.entity.Proposta;
import com.ebr.proposta_app.mapper.PropostaMapper;
import com.ebr.proposta_app.repository.PropostaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;
    private final NotificacaoService notificacaoService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;


    @Transactional
    public PropostaDTO salvar(PropostaDTO dto) {
        Proposta proposta = PropostaMapper.INSTANCE.converterDtoParaProposta(dto);
        repository.save(proposta);

        PropostaDTO propostaDTO = PropostaMapper.INSTANCE.converterEntidadeParaDto(proposta);
        notificacaoService.notificar(propostaDTO, exchange);
        return propostaDTO;
    }

    public List<PropostaDTO> obterPropostas() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }
}
