package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubstituicaoData {

    @NotNull(message = "O ID do discente que está saindo é obrigatório")
    private Integer idDiscenteSaindo;

    @NotNull(message = "O ID do discente que está entrando é obrigatório")
    private Integer idDiscenteEntrando;

    @NotBlank(message = "A justificativa é obrigatória")
    private String justificativa;

    // Construtores, Getters e Setters (Se estiver usando Lombok, basta colocar @Data no topo)
    public SubstituicaoData() {}

    public Integer getIdDiscenteSaindo() { return idDiscenteSaindo; }
    public void setIdDiscenteSaindo(Integer idDiscenteSaindo) { this.idDiscenteSaindo = idDiscenteSaindo; }

    public Integer getIdDiscenteEntrando() { return idDiscenteEntrando; }
    public void setIdDiscenteEntrando(Integer idDiscenteEntrando) { this.idDiscenteEntrando = idDiscenteEntrando; }

    public String getJustificativa() { return justificativa; }
    public void setJustificativa(String justificativa) { this.justificativa = justificativa; }
}