package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Discente extends Usuario {
    @Column(unique = true)
    private String matricula;

    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Discente(String nome, String email, String senha, String matricula, int semestre, Curso curso, Papel papel) {
        super(nome, email, senha, papel);
        this.matricula = matricula;
        this.semestre = semestre;
        this.curso = curso;
    }

    public Discente(String nome, String email, String senha, String matricula, int semestre, Curso curso) {
        super(nome, email, senha, null) ;
        this.matricula = matricula;
        this.semestre = semestre;
        this.curso = curso;
    }

    public Discente(){}

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Integer getSemestre() {
        return semestre;
    }
    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public void anonimizar(){
        super.anonimizar();
        this.matricula = "ANONIMO-" + System.currentTimeMillis();
    }
}
