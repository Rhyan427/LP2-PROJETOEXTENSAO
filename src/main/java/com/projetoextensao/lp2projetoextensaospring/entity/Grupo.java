package com.projetoextensao.lp2projetoextensaospring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String objetivos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_grupo", nullable = false)
    private StatusGrupo status;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Docente responsavel;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private List<HistoricoCargo> historicoCargos = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getObjetivos(){
        return objetivos;
    }
    public void setObjetivos(String objetivos){
        this.objetivos = objetivos;
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
    public List<HistoricoCargo> getHistoricoCargos() { return historicoCargos; }
    public void setHistoricoCargos(ArrayList<HistoricoCargo> historicoCargos) { this.historicoCargos = historicoCargos; }

    public Grupo(String nome, String descricao, String email, Docente responsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.status = StatusGrupo.ATIVO;
        this.responsavel = responsavel;
        this.historicoCargos = new ArrayList<>();
    }

    public Grupo(){}
}
