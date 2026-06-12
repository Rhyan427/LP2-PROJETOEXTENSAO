package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PPC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "horas_extensao", nullable = false)
    private Integer horasExtensao;

    @Column(nullable = false)
    private LocalDate vigencia;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getHorasExtensao() {
        return horasExtensao;
    }
    public void setHorasExtensao(Integer horasExtensao) {
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

    public PPC(){}
}
