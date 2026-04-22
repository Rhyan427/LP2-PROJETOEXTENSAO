package entity;

import java.time.LocalDate;

public class DiscenteDiretor extends Discente {
    private Grupo grupo;
    private String cargo;
    private LocalDate dataInicio;
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

    public DiscenteDiretor(String nome, String email, String senha, String matricula, int semestre, Curso curso, Grupo grupo, String cargo, LocalDate dataInicio, LocalDate dataFim) {
        super(nome, email, senha, matricula, semestre, curso);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        Papel papel = new Papel("Discente Diretor");
        super.setPapel(papel);
    }
    public DiscenteDiretor() {} //construtor para login
}
