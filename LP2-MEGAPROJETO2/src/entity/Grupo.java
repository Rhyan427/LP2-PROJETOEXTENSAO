package entity;

import java.util.ArrayList;

public class Grupo {
    private String nome;
    private String descricao;
    private String email;
    private StatusGrupo status;
    private Docente responsavel;
    private ArrayList<Discente> membros;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public StatusGrupo getStatus() {
        return status;
    }
    public void setStatus(StatusGrupo status) {
        this.status = status;
    }
    public Docente getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }
    public ArrayList<Discente> getMembros() {
        return membros;
    }
    public void setMembros(ArrayList<Discente> membros) {
        this.membros = membros;
    }

    public Grupo(String nome, String descricao, String email, Docente responsavel, ArrayList<Discente> membros) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.status = StatusGrupo.ATIVO;
        this.responsavel = responsavel;
        this.membros = membros;
    }
}
