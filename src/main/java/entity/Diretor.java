package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Diretor extends Discente {

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @Column(nullable = false)
    private String cargo;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Diretor(String nome, String email, String senha, String matricula, int semestre, Curso curso, Grupo grupo, String cargo, LocalDate dataInicio, LocalDate dataFim) {
        super(nome, email, senha, matricula, semestre, curso);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        Papel papel = new Papel("Discente Diretor");
        super.setPapel(papel);
    }

    public Diretor() {}
}
