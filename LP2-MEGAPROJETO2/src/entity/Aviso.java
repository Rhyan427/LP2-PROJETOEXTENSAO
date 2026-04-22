package entity;

import java.time.LocalDate;

public class Aviso {
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

    public Aviso(String titulo, String mensagem, Usuario autor) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.dataPublicacao = LocalDate.now();
    }
}
