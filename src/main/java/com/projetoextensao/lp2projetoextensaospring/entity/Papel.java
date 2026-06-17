package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;


@Entity
public class Papel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String descricao;

    public Papel(String descricao){
        this.descricao = descricao;
    }

    public Papel(){}

    public Integer GetId(){ return id; }
    public void setId(Integer id){ this.id = id; }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(){
        this.descricao = descricao;
    }
}
