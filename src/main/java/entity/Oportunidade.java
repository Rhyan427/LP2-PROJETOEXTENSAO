package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Oportunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_oportunidade", nullable = false)
    private TipoOportunidade tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidade modalidade;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(nullable = false)
    private Integer vagas;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_oportunidade", nullable = false)
    private StatusOportunidade status;

    @Column(name = "data_plano_atividades")
    private LocalDate dataPlanoAtividades;

    @Column(nullable = false)
    private LocalDate inicio;

    @Column(nullable = false)
    private LocalDate fim;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Docente responsavel;

    @Column(columnDefinition = "TEXT")
    private String plano;

    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public TipoOportunidade getTipo() {
        return tipo;
    }
    public void setTipo(TipoOportunidade tipo) {
        this.tipo = tipo;
    }
    public Modalidade getModalidade() {
        return modalidade;
    }
    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public Integer getVagas() {
        return vagas;
    }
    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }
    public StatusOportunidade getStatus() {
        return status;
    }
    public void setStatus(StatusOportunidade status) {
        this.status = status;
    }
    public LocalDate getDataPlanoAtividades() {
        return dataPlanoAtividades;
    }
    public void setDataPlanoAtividades(LocalDate dataPlanoAtividades) {
        this.dataPlanoAtividades = dataPlanoAtividades;
    }
    public LocalDate getInicio() {
        return inicio;
    }
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }
    public LocalDate getFim() {
        return fim;
    }
    public void setFim(LocalDate fim) {
        this.fim = fim;
    }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
    public Docente getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }
    public String getPlano() {
        return plano;
    }
    public void setPlano(String plano) {
        this.plano = plano;
    }

    public Oportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate dataPlanoAtividades, LocalDate inicio, LocalDate fim, Usuario autor, Docente responsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.status = StatusOportunidade.RASCUNHO;
        this.dataPlanoAtividades = dataPlanoAtividades;
        this.inicio = inicio;
        this.fim = fim;
        this.autor = autor;
        this.responsavel = responsavel;
        this.plano = "Atente-se aos prazos.";
    }

    public Oportunidade(){}
}

