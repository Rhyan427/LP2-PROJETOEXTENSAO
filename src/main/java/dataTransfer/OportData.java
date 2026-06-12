package dataTransfer;

import entity.*;

import java.time.LocalDate;

public class OportData {
    private String titulo;
    private String descricao;
    private TipoOportunidade tipo;
    private Modalidade modalidade;
    private int cargaHoraria;
    private int vagas;
    private StatusOportunidade status;
    private LocalDate dataPlanoAtividades;
    private LocalDate inicio;
    private LocalDate fim;
    private Usuario autor;
    private Docente responsavel;
    private String plano;

    public OportData(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, StatusOportunidade status, LocalDate dataPlanoAtividades, LocalDate inicio, LocalDate fim, Usuario autor, Docente responsavel, String plano) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.status = status;
        this.dataPlanoAtividades = dataPlanoAtividades;
        this.inicio = inicio;
        this.fim = fim;
        this.autor = autor;
        this.responsavel = responsavel;
        this.plano = plano;
    }

    public OportData(String titulo, TipoOportunidade tipo, Modalidade modalidade, int vagas, int cargaHoraria, StatusOportunidade status) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.status = status;
    }

    public OportData(Oportunidade oportunidade) {
        this.titulo = oportunidade.getTitulo();
        this.descricao = oportunidade.getDescricao();
        this.tipo = oportunidade.getTipo();
        this.modalidade = oportunidade.getModalidade();
        this.cargaHoraria = oportunidade.getCargaHoraria();
        this.vagas = oportunidade.getVagas();
        this.status = oportunidade.getStatus();
        this.dataPlanoAtividades = oportunidade.getDataPlanoAtividades();
        this.inicio = oportunidade.getInicio();
        this.fim = oportunidade.getFim();
        this.autor = oportunidade.getAutor();
        this.responsavel = oportunidade.getResponsavel();
        this.plano = oportunidade.getPlano();
    }

    public OportData() {}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public TipoOportunidade getTipo() { return tipo; }
    public void setTipo(TipoOportunidade tipo) { this.tipo = tipo; }
    public Modalidade getModalidade() { return modalidade; }
    public void setModalidade(Modalidade modalidade) { this.modalidade = modalidade; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public int getVagas() { return vagas; }
    public void setVagas(int vagas) { this.vagas = vagas; }
    public StatusOportunidade getStatus() { return status; }
    public void setStatus(StatusOportunidade status) { this.status = status; }
    public LocalDate getDataPlanoAtividades() { return dataPlanoAtividades; }
    public void setDataPlanoAtividades(LocalDate dataPlanoAtividades) { this.dataPlanoAtividades = dataPlanoAtividades; }
    public LocalDate getInicio() { return inicio; }
    public void setInicio(LocalDate inicio) { this.inicio = inicio; }
    public LocalDate getFim() { return fim; }
    public void setFim(LocalDate fim) { this.fim = fim; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public Docente getResponsavel() { return responsavel; }
    public void setResponsavel(Docente responsavel) { this.responsavel = responsavel; }
    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
}
