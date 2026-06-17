package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "discente_id", nullable = false)
    private Discente discente;

    @ManyToOne
    @JoinColumn(name = "oportunidade_id", nullable = false)
    private Oportunidade oportunidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_inscricao", nullable = false)
    private StatusInscricao status;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @Column(columnDefinition = "TEXT")
    private String justificativa;

    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }
    public Discente getDiscente() {
        return discente;
    }
    public void setDiscente(Discente discente) {
        this.discente = discente;
    }
    public Oportunidade getOportunidade() {
        return oportunidade;
    }
    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }
    public StatusInscricao getStatus() {
        return status;
    }
    public void setStatus(StatusInscricao status) {
        this.status = status;
    }
    public LocalDate getDataInscricao() {
        return dataInscricao;
    }
    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
    public String getJustificativa() { return justificativa; }
    public void setJustificativa(String justificativa) { this.justificativa = justificativa; }

    public Inscricao(Discente discente, Oportunidade oportunidade, StatusInscricao status, LocalDate dataInscricao) {
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.status = status;
        this.dataInscricao = dataInscricao;
        this.justificativa = "N/A";
    }

    public Inscricao(){}
}

