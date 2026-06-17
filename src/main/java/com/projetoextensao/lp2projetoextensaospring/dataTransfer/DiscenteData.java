package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Curso;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Papel;

import java.util.Objects;

public class DiscenteData {
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private Papel papel;
    private String matricula;
    private int semestre;
    private Curso curso;

    public DiscenteData(){}

    public DiscenteData(String nome, String email, String senha, boolean ativo, Papel papel, String matricula, int semestre, Curso curso) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.papel = papel;
        this.matricula = matricula;
        this.semestre = semestre;
        this.curso = curso;
    }

    public DiscenteData(Discente discente) {
        this.nome = discente.getNome();
        this.email = discente.getEmail();
        this.senha = discente.getSenha();
        this.ativo = discente.isAtivo();
        this.papel = discente.getPapel();
        this.matricula = discente.getMatricula();
        this.semestre = discente.getSemestre();
        this.curso = discente.getCurso();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Papel getPapel() { return papel; }
    public void setPapel(Papel papel) { this.papel = papel; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DiscenteData that = (DiscenteData) o;
        return ativo == that.ativo && semestre == that.semestre && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && Objects.equals(papel, that.papel) && Objects.equals(matricula, that.matricula) && Objects.equals(curso, that.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha, ativo, papel, matricula, semestre, curso);
    }
}
