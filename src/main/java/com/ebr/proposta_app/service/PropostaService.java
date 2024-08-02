package com.ebr.proposta_app.service;

import com.ebr.proposta_app.dto.PropostaDTO;
import com.ebr.proposta_app.entity.Proposta;
import com.ebr.proposta_app.mapper.PropostaMapper;
import com.ebr.proposta_app.repository.PropostaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;

    @Transactional
    public PropostaDTO salvar(PropostaDTO dto) {
        Proposta proposta = PropostaMapper.INSTANCE.converterDtoParaProposta(dto);
        repository.save(proposta);

        return PropostaMapper.INSTANCE.converterEntidadeParaDto(proposta);
    }

}
