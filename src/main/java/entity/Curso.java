package entity;

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

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @OneToOne
    @JoinColumn(name = "ppc_atual_id")
    private PPC ppcAtual;

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
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public PPC getPpcAtual() {
        return ppcAtual;
    }
    public void setPpcAtual(PPC ppcAtual) {
        this.ppcAtual = ppcAtual;
    }
    public List<Discente> getAlunos() {
        return alunos;
    }
    public void setAlunos(List<Discente> alunos) {
        this.alunos = alunos;
    }

    public Curso(String nome, int codigo, int cargaHoraria, PPC ppcAtual) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.ppcAtual = ppcAtual;
    }

    public Curso(){}
}