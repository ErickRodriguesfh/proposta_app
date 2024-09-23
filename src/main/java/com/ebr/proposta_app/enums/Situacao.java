package com.ebr.proposta_app.enums;

public enum Situacao {

    EM_ANALISE("Em análise"),
    APROVADA("Aprovada"),
    RECUSADA("Recusada");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Situacao fromKey(String key) {
        for (Situacao status : Situacao.values()) {
            if (status.name().equalsIgnoreCase(key)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Chave inválida: " + key);
    }

}
