package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Grupo;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusGrupo;
import jakarta.validation.constraints.*;

import java.util.HashMap;

public class GrupoData {

    @NotBlank(message = "O nome do grupo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do grupo deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 20, max = 3000, message = "O preenchimento deste campo deve ter entre 20 e 3000 caracteres")
    private String descricao;

    @NotBlank(message = "O(s) objetivo(s) é obrigatório")
    @Size(min = 20, max = 3000, message = " O preenchimento deste campo deve ter entre 20 e 3000 caracteres")
    private String objetivos;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    private StatusGrupo status;

    @NotBlank(message = "É obrigatório informar o docente responsável")
    private Docente responsavel;

    @NotBlank(message = "O grupo deve ter pelo menos 1 membro")
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
