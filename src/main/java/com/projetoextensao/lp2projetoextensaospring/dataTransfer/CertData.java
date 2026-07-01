package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import jakarta.validation.constraints.NotNull;

public class CertData {

    @NotNull(message = "O ID do discente é obrigatório")
    private Integer idDiscente;

    @NotNull(message = "O ID da oportunidade é obrigatório")
    private Integer idOportunidade;

    public CertData() {}

    public Integer getIdDiscente() { return idDiscente; }
    public void setIdDiscente(Integer idDiscente) { this.idDiscente = idDiscente; }
    public Integer getIdOportunidade() { return idOportunidade; }
    public void setIdOportunidade(Integer idOportunidade) { this.idOportunidade = idOportunidade; }
}