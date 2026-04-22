package entity;

import java.time.LocalDate;

public class Inscricao {
    private Discente discente;
    private Oportunidade oportunidade;
    private StatusInscricao status;
    private LocalDate dataInscricao;

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

    public Inscricao(Discente discente, Oportunidade oportunidade, StatusInscricao status, LocalDate dataInscricao) {
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.status = status;
        this.dataInscricao = dataInscricao;
    }
}
