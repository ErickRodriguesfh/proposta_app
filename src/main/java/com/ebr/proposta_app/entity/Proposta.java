package com.ebr.proposta_app.entity;

import com.ebr.proposta_app.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_proposta")
@Data
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_solicitado")
    private Double valorSolicitado;

    @Column(name = "prazo_pagamento")
    private Integer prazoPagamento;

    private Boolean aprovada;

    private Boolean integrada;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

    @PrePersist
    public void antesSalvar() {
        this.aprovada = false;
        this.situacao = Situacao.EM_ANALISE;
    }

}
