package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

public class ReenviarData {
    private String novaDescricao;
    private Integer novasHoras;

    public ReenviarData(String novaDescricao, Integer novasHoras) {
        this.novaDescricao = novaDescricao;
        this.novasHoras = novasHoras;
    }

    public String getNovaDescricao() {
        return novaDescricao;
    }

    public void setNovaDescricao(String novaDescricao) {
        this.novaDescricao = novaDescricao;
    }

    public Integer getNovasHoras() {
        return novasHoras;
    }

    public void setNovasHoras(Integer novasHoras) {
        this.novasHoras = novasHoras;
    }
}
