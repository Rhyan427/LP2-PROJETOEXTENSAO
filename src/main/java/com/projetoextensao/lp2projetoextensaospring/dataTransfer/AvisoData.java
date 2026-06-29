package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;

import java.time.LocalDate;

public class AvisoData {
    private String titulo;
    private String mensagem;
    private Usuario autor;
    private LocalDate dataPublicacao;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }

    public AvisoData(String titulo, String mensagem, Usuario autor, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.dataPublicacao = LocalDate.now();
    }

    public AvisoData(String titulo, String mensagem, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataPublicacao = LocalDate.now();
    }

}
