package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Aproveitamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aproveitamento_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "discente_id", nullable = false)
    private Discente discente;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String instituicao;
    private Integer horas;

    @Enumerated(EnumType.STRING)
    private StatusAproveitamento status;

    @OneToOne
    @JoinColumn(name = "certificado_uuid")
    private Certificado certificado;


    private String motivo_rejeicao;

    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao; // preenchida ao criar

    @Column(name = "data_limite_decisao")
    private LocalDate dataLimiteDecisao; // dataSolicitacao + 10 dias

    @Column(name = "data_limite_reenvio")
    private LocalDate dataLimiteReenvio; // data indeferimento + 5 dias (null até ser indeferido)

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    public StatusAproveitamento getStatus() { return status; }
    public void setStatus(StatusAproveitamento status) { this.status = status; }

    public Certificado getCertificado() { return certificado; }
    public void setCertificado(Certificado certificado) { this.certificado = certificado; }

    public String getMotivo_rejeicao() { return motivo_rejeicao; }
    public void setMotivo_rejeicao(String motivo_rejeicao) { this.motivo_rejeicao = motivo_rejeicao; }

    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public LocalDate getDataLimiteDecisao() { return dataLimiteDecisao; }

    public LocalDate getDataLimiteReenvio() { return dataLimiteReenvio; }
    public void setDataLimiteReenvio(LocalDate dataLimiteReenvio) { this.dataLimiteReenvio = dataLimiteReenvio; }

    public Aproveitamento(Discente discente, String descricao, int horas, StatusAproveitamento status, Certificado certificado) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = "UUUFMA";
        this.horas = horas;
        this.status = status;
        this.certificado = certificado;
        this.motivo_rejeicao = "N/A";
        // RF022 — prazos definidos automaticamente na criação
        this.dataSolicitacao = LocalDate.now();
        this.dataLimiteDecisao = dataSolicitacao.plusDays(10);
        this.dataLimiteReenvio = null;
    }

    public Aproveitamento(){}
}