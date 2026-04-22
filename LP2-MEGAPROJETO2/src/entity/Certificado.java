package entity;

import java.time.LocalDate;
import java.util.UUID;

public class Certificado {
    private final String uuidHash;
    private Discente discente;
    private Oportunidade oportunidade;
    private LocalDate dataEmissao;
    private int horas;
    private String certificadoPath;
    private StatusAssinatura statusAssinatura;

    public String getUuidHash() {
        return uuidHash;
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
    public LocalDate getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    public int getHoras() {
        return horas;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public String getCertificadoPath() {
        return certificadoPath;
    }
    public void setCertificadoPath(String certificadoPath) {
        this.certificadoPath = certificadoPath;
    }
    public StatusAssinatura getStatusAssinatura() {
        return statusAssinatura;
    }
    public void setStatusAssinatura(StatusAssinatura statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }

    public Certificado(Discente discente, Oportunidade oportunidade, int horas, String certificadoPath) {
        this.uuidHash = UUID.randomUUID().toString();
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = LocalDate.now();
        this.horas = horas;
        this.certificadoPath = certificadoPath;
        this.statusAssinatura = StatusAssinatura.PENDENTE;
    }
}