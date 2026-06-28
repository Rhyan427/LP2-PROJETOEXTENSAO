package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Inscricao;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusInscricao;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class InscricaoData {

    @NotNull(message = "Os dados do discente são obrigatórios para a inscrição")
    private Discente discente;

    @NotNull(message = "A oportunidade desejada é obrigatória")
    private Oportunidade oportunidade;

    @NotNull(message = "O status da inscrição tem de ser informado")
    private StatusInscricao status;

    @NotNull(message = "A data da inscrição é obrigatória")
    @PastOrPresent(message = "A data da inscrição não pode estar no futuro")
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
