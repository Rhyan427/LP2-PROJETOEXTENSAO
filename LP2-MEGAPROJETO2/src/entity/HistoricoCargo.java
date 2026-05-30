package entity;

import java.time.LocalDate;

public class HistoricoCargo {
    private Discente discente;
    private Grupo grupo;
    private CargoGrupo cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public HistoricoCargo(Discente discente, Grupo grupo, CargoGrupo cargo) {
        this.discente = discente;
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = LocalDate.now(); // O cargo começa no momento da atribuição
        this.dataFim = null;
    }


    public Discente getDiscente() { return discente; }
    public Grupo getGrupo() { return grupo; }
    public CargoGrupo getCargo() { return cargo; }
    public void setCargo(CargoGrupo cargo) { this.cargo = cargo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}