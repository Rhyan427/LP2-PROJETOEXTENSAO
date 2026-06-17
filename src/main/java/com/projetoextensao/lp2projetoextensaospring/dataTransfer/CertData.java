package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Certificado;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusAssinatura;

import java.time.LocalDate;

public class CertData {
    private String uuidHash;
    private Discente discente;
    private Oportunidade oportunidade;
    private LocalDate dataEmissao;
    private int horas;
    private String certificadoPath;
    private StatusAssinatura statusAssinatura;

    public CertData(String uuidHash, Discente discente, Oportunidade oportunidade, LocalDate dataEmissao, int horas, String certificadoPath, StatusAssinatura statusAssinatura) {
        this.uuidHash = uuidHash;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = dataEmissao;
        this.horas = horas;
        this.certificadoPath = certificadoPath;
        this.statusAssinatura = statusAssinatura;
    }

    public CertData(Discente discente, Oportunidade oportunidade, int horas, String certificadoPath) {
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.horas = horas;
        this.certificadoPath = certificadoPath;
    }

    public CertData(Certificado certificado) {
        this.uuidHash = certificado.getUuidHash();
        this.discente = certificado.getDiscente();
        this.oportunidade = certificado.getOportunidade();
        this.dataEmissao = certificado.getDataEmissao();
        this.horas = certificado.getHoras();
        this.certificadoPath = certificado.getCertificadoPath();
        this.statusAssinatura = certificado.getStatusAssinatura();
    }

    public CertData() {}

    public String getUuidHash() { return uuidHash; }
    public void setUuidHash(String uuidHash) { this.uuidHash = uuidHash; }
    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }
    public Oportunidade getOportunidade() { return oportunidade; }
    public void setOportunidade(Oportunidade oportunidade) { this.oportunidade = oportunidade; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public int getHoras() { return horas; }
    public void setHoras(int horas) { this.horas = horas; }
    public String getCertificadoPath() { return certificadoPath; }
    public void setCertificadoPath(String certificadoPath) { this.certificadoPath = certificadoPath; }
    public StatusAssinatura getStatusAssinatura() { return statusAssinatura; }
    public void setStatusAssinatura(StatusAssinatura statusAssinatura) { this.statusAssinatura = statusAssinatura; }
}
