package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Docente extends Usuario {
    @Column(unique = true, nullable = false)
    private String siape;

    @Column(nullable = false)
    private String departamento;

    public Docente(String nome, String email, String senha, String siape, String departamento, Papel papel) {
        super(nome, email, senha, papel);
        this.siape = siape;
        this.departamento = departamento;
    }

    public Docente(String nome, String email, String senha, Papel papel, String siape, String departamento) {
        super(nome, email, senha, papel);
        this.siape = siape;
        this.departamento = departamento;
    } //construtor para coordenador

    public Docente(String nome, String email, String senha, String siape, String departamento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.siape = siape;
        this.departamento = departamento;
    }

    public Docente(){}

    public String getSiape() {
        return siape;
    }
    public void setSiape(String siape) {
        this.siape = siape;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
