package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_hash", length = 36)
    private String uuidHash;

    @ManyToOne
    @JoinColumn(name = "discente_id", nullable = false)
    private Discente discente;

    @ManyToOne
    @JoinColumn(name = "oportunidade_id")
    private Oportunidade oportunidade;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    private int horas;

    @Column(name = "certificado_path")
    private String certificadoPath;

    @Enumerated(EnumType.STRING)
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
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.dataEmissao = LocalDate.now();
        this.horas = horas;
        this.certificadoPath = certificadoPath;
        this.statusAssinatura = StatusAssinatura.PENDENTE;
    }

    public Certificado(){}
}
