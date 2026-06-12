package dataTransfer;

import entity.Discente;
import entity.Inscricao;
import entity.Oportunidade;
import entity.StatusInscricao;

import java.time.LocalDate;

public class InscricaoData {
    private Discente discente;
    private Oportunidade oportunidade;
    private StatusInscricao status;
    private LocalDate dataInscricao;

    public InscricaoData(Discente discente, Oportunidade oportunidade, StatusInscricao status, LocalDate dataInscricao) {
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.status = status;
        this.dataInscricao = dataInscricao;
    }

    public InscricaoData(Discente discente, Oportunidade oportunidade, StatusInscricao status) {
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.status = status;
    }

    public InscricaoData(Inscricao inscricao) {
        this.discente = inscricao.getDiscente();
        this.oportunidade = inscricao.getOportunidade();
        this.status = inscricao.getStatus();
        this.dataInscricao = inscricao.getDataInscricao();
    }

    public InscricaoData() {}

    public Discente getDiscente() { return discente; }
    public void setDiscente(Discente discente) { this.discente = discente; }
    public Oportunidade getOportunidade() { return oportunidade; }
    public void setOportunidade(Oportunidade oportunidade) { this.oportunidade = oportunidade; }
    public StatusInscricao getStatus() { return status; }
    public void setStatus(StatusInscricao status) { this.status = status; }
    public LocalDate getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDate dataInscricao) { this.dataInscricao = dataInscricao; }
}
