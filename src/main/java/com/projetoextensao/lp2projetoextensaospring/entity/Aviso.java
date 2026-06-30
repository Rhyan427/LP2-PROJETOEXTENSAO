package com.projetoextensao.lp2projetoextensaospring.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aviso_id")
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Aviso(String titulo, String mensagem, Usuario autor) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.dataPublicacao = LocalDate.now();
    }

    public Aviso(){}
}
