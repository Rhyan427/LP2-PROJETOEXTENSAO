package entity;

import java.time.LocalDate;

public class PPC {
    private Usuario autor;
    private String descricao;
    private int horasExtensao;
    private LocalDate vigencia;

    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getHorasExtensao() {
        return horasExtensao;
    }
    public void setHorasExtensao(int horasExtensao) {
        this.horasExtensao = horasExtensao;
    }
    public LocalDate getVigencia() {
        return vigencia;
    }
    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public PPC(Usuario autor, String descricao, int horasExtensao, LocalDate vigencia) {
        this.autor = autor;
        this.descricao = descricao;
        this.horasExtensao = horasExtensao;
        this.vigencia = vigencia;
    }
}
