package com.ebr.proposta_app.dto;

import com.ebr.proposta_app.enums.Situacao;

public record PropostaDTO(String nome, String sobrenome, String telefone, String cpf, Double renda,
                          Double valorSolicitado, Integer prazoPagamento, Boolean aprovada,
                          String observacao, Long id, String situacao) {
}
