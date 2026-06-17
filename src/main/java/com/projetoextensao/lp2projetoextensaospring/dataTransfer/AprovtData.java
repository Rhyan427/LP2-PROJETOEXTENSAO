package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Aproveitamento;
import com.projetoextensao.lp2projetoextensaospring.entity.Certificado;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusAproveitamento;

import java.time.LocalDate;

public class AprovtData {
    private Discente discente;
    private String descricao;
    private String instituicao;
    private int horas;
    private StatusAproveitamento status;
    private Certificado certificado;
    private String motivo_rejeicao;
    private LocalDate dataLimiteDecisao;
    private LocalDate dataLimiteReenvio;

    public AprovtData(Discente discente, String descricao, String instituicao, int horas,
                      StatusAproveitamento status, Certificado certificado, String motivo_rejeicao) {
        this.discente = discente;
        this.descricao = descricao;
        this.instituicao = instituicao;
        this.horas = horas;
        this.status = status;
        this.certificado = certificado;
        this.motivo_rejeicao = motivo_rejeicao;
    }

    public AprovtData(Discente discente, int horas, StatusAproveitamento status, Certificado certificado) {
        this.discente = discente;
        this.horas = horas;
        this.status = status;
        this.certificado = certificado;
    }

    public AprovtData(Aproveitamento aproveitamento) {
        this.discente = aproveitamento.getDiscente();
        this.descricao = aproveitamento.getDescricao();
        this.instituicao = aproveitamento.getInstituicao();
        this.horas = aproveitamento.getHoras();
        this.status = aproveitamento.getStatus();
        this.certificado = aproveitamento.getCertificado();
        this.motivo_rejeicao = aproveitamento.getMotivo_rejeicao();
        this.dataLimiteDecisao = aproveitamento.getDataLimiteDecisao();
        this.dataLimiteReenvio = aproveitamento.getDataLimiteReenvio();
    }

    public AprovtData() {}

    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
    public int getHoras() { return horas; }
    public void setHoras(int horas) { this.horas = horas; }
    public StatusAproveitamento getStatus() { return status; }
    public void setStatus(StatusAproveitamento status) { this.status = status; }
    public Certificado getCertificado() { return certificado; }
    public void setCertificado(Certificado certificado) { this.certificado = certificado; }
    public String getMotivo_rejeicao() { return motivo_rejeicao; }
    public void setMotivo_rejeicao(String motivo_rejeicao) { this.motivo_rejeicao = motivo_rejeicao; }
    public LocalDate getDataLimiteDecisao() { return dataLimiteDecisao; }
    public void setDataLimiteDecisao(LocalDate dataLimiteDecisao) { this.dataLimiteDecisao = dataLimiteDecisao; }
    public LocalDate getDataLimiteReenvio() { return dataLimiteReenvio; }
    public void setDataLimiteReenvio(LocalDate dataLimiteReenvio) { this.dataLimiteReenvio = dataLimiteReenvio; }
}
