package com.ebr.proposta_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_proposta")
@Data
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;

    private Integer prazoPagamento;

    private Boolean aprovada;

    private Boolean integrada;

    private String observacao;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
