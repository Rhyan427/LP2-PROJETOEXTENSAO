package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Curso;
import com.projetoextensao.lp2projetoextensaospring.entity.Diretor;
import com.projetoextensao.lp2projetoextensaospring.entity.Grupo;
import com.projetoextensao.lp2projetoextensaospring.entity.Papel;

import java.time.LocalDate;

public class DiretorData {
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private Papel papel;
    private String matricula;
    private int semestre;
    private Curso curso;
    private Grupo grupo;
    private String cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public DiretorData(String nome, String email, String senha, boolean ativo, Papel papel, String matricula, int semestre, Curso curso, Grupo grupo, String cargo, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.papel = papel;
        this.matricula = matricula;
        this.semestre = semestre;
        this.curso = curso;
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public DiretorData(Diretor diretor) {
        this.nome = diretor.getNome();
        this.email = diretor.getEmail();
        this.senha = diretor.getSenha();
        this.ativo = diretor.isAtivo();
        this.papel = diretor.getPapel();
        this.matricula = diretor.getMatricula();
        this.semestre = diretor.getSemestre();
        this.curso = diretor.getCurso();
        this.grupo = diretor.getGrupo();
        this.cargo = diretor.getCargo();
        this.dataInicio = diretor.getDataInicio();
        this.dataFim = diretor.getDataFim();
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
    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public DiretorData() {}
}
