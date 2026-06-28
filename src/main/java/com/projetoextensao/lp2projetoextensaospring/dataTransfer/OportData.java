package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class OportData {
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 5, max = 150, message = "O título deve ter entre 5 e 150 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 20, message = "A descrição deve conter pelo menos 20 caracteres detalhando a oportunidade")
    private String descricao;

    @NotNull(message = "O tipo de oportunidade (PROJETO, CURSO, EVENTO ou OFICINA) é obrigatório")
    private TipoOportunidade tipo;

    @NotNull(message = "A modalidade (PRESENCIAL, REMOTO ou HIBRIDO) é obrigatória")
    private Modalidade modalidade;

    @NotNull(message = "A carga horária é obrigatória")
    @Positive(message = "A carga horária deve ser um número positivo (maior que zero)")
    private Integer cargaHoraria;


    @NotNull(message = "O número de vagas é obrigatório")
    @Min(value = 1, message = "A oportunidade deve ter, pelo menos, 1 vaga disponível")
    private Integer vagas;

    private StatusOportunidade status;

    @FutureOrPresent(message = "A data do plano de atividades não pode estar no passado")
    private LocalDate dataPlanoAtividades;

    @NotNull(message = "A data de início é obrigatória")
    @FutureOrPresent(message = "A data de início da oportunidade não pode estar no passado")
    private LocalDate inicio;

    @NotNull(message = "A data de fim é obrigatória")
    @Future(message = "A data de fim da oportunidade deve estar no futuro")
    private LocalDate fim;

    @NotNull(message = "O autor da oportunidade tem de ser informado")
    private Docente autor;

    @NotNull(message = "O docente responsável tem de ser informado")
    private Docente responsavel;

    @NotBlank(message = "O plano da oportunidade não pode estar vazio")
    private String plano;

    public OportData(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, StatusOportunidade status, LocalDate dataPlanoAtividades, LocalDate inicio, LocalDate fim, Docente autor, Docente responsavel, String plano) {
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
        this.autor = (Docente) oportunidade.getAutor();
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
    public Docente getAutor() { return autor; }
    public void setAutor(Docente autor) { this.autor = autor; }
    public Docente getResponsavel() { return responsavel; }
    public void setResponsavel(Docente responsavel) { this.responsavel = responsavel; }
    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
}
