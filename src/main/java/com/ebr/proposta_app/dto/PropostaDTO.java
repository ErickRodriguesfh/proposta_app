package com.ebr.proposta_app.dto;

public record PropostaDTO(String nome, String sobrenome, String telefone, String cpf, Double renda,
                          Double valorSolicitado, Integer prazoPagamento, Boolean aprovada,
                          String observacao, Long id) {
}
