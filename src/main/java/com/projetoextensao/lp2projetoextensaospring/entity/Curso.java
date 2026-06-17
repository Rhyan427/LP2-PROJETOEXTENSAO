package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Integer id;

    private String nome;

    @Column(unique = true)
    private Integer codigo;

    @Column(name = "ano_ppc")
    private Integer anoPPC;

    @Column(name = "carga_horaria_total")
    private Integer cargaHorariaTotal;

    @Column(name = "carga_horaria_extensao")
    private Integer cargaHorariaExtensao;

    @Column(name = "status_ppc")
    private String statusPPC;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Discente> alunos;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public List<Discente> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<Discente> alunos) {
        this.alunos = alunos;
    }
    public Integer getAnoPPC() { return anoPPC; }
    public void setAnoPPC(Integer anoPPC) { this.anoPPC = anoPPC; }
    public Integer getCargaHorariaTotal() { return cargaHorariaTotal; }
    public void setCargaHorariaTotal(Integer cargaHorariaTotal) { this.cargaHorariaTotal = cargaHorariaTotal; }
    public Integer getCargaHorariaExtensao() { return cargaHorariaExtensao; }
    public void setCargaHorariaExtensao(Integer cargaHorariaExtensao) { this.cargaHorariaExtensao = cargaHorariaExtensao; }
    public String getStatusPPC() { return statusPPC; }
    public void setStatusPPC(String statusPPC) { this.statusPPC = statusPPC; }

    public Curso(String nome, Integer anoPPC, Integer cargaHorariaTotal, Integer cargaHorariaExtensao, String statusPPC) {
        this.nome = nome;
        this.anoPPC = anoPPC;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.cargaHorariaExtensao = cargaHorariaExtensao;
        this.statusPPC = statusPPC;
    }

    public Curso(){}
}