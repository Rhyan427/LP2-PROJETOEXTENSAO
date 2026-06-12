package dataTransfer;

import entity.Discente;
import entity.Docente;
import entity.Grupo;
import entity.StatusGrupo;

import java.util.HashMap;

public class GrupoData {
    private String nome;
    private String descricao;
    private String objetivos;
    private String email;
    private StatusGrupo status;
    private Docente responsavel;
    private HashMap<String, Discente> membros;

    public GrupoData(String nome, String descricao, String email, Docente responsavel, HashMap<String, Discente> membros) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.status = StatusGrupo.ATIVO;
        this.responsavel = responsavel;
        this.membros = membros;
    }

    public GrupoData(String nome, Docente responsavel, HashMap<String, Discente> membros) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.membros = membros;
    }

    public GrupoData(String nome, String descricao, String objetivos, Docente responsavel, Discente discenteSolicitante) {
        this.nome = nome;
        this.descricao = descricao;
        this.objetivos = objetivos;
        this.responsavel = responsavel;
        this.status = StatusGrupo.PENDENTE;
        this.membros = new HashMap<>();
        if (discenteSolicitante != null) {
            this.membros.put(discenteSolicitante.getMatricula(), discenteSolicitante);
        }
    }

    public GrupoData(Grupo grupo) {
        this.nome = grupo.getNome();
        this.descricao = grupo.getDescricao();
        this.objetivos = grupo.getObjetivos();
        this.email = grupo.getEmail();
        this.status = grupo.getStatus();
        this.responsavel = grupo.getResponsavel();
    }

    public GrupoData() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getObjetivos() { return objetivos; }
    public void setObjetivos(String objetivos) { this.objetivos = objetivos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public StatusGrupo getStatus() { return status; }
    public void setStatus(StatusGrupo status) { this.status = status; }
    public Docente getResponsavel() { return responsavel; }
    public void setResponsavel(Docente responsavel) { this.responsavel = responsavel; }
}
