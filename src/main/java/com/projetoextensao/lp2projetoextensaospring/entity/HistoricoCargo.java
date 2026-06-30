package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HistoricoCargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "discente_id", nullable = false)
    private Discente discente;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CargoGrupo cargo;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    public HistoricoCargo(Discente discente, Grupo grupo, CargoGrupo cargo) {
        this.discente = discente;
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = LocalDate.now(); // O cargo começa no momento da atribuição
        this.dataFim = dataInicio.plusDays(30);
    }

    public HistoricoCargo(){}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Discente getDiscente() { return discente; }
    public Grupo getGrupo() { return grupo; }
    public CargoGrupo getCargo() { return cargo; }
    public void setCargo(CargoGrupo cargo) { this.cargo = cargo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}