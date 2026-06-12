package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String senha;

    protected boolean ativo;

    @ManyToOne
    @JoinColumn(name = "papel_id")
    protected Papel papel;

    public Usuario(String nome, String email, String senha, Papel papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = true;
    }

    public Usuario(){}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Papel getPapel() {
        return papel;
    }
    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return ativo == usuario.ativo && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(papel, usuario.papel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha, ativo, papel);
    }

    public void anonimizar(){
        this.nome = "Usuario anonimizado";
        this.email = "anonimo_ " + System.currentTimeMillis() + "@instituicao.edu.br";
        this.senha =  "*****";
        this.ativo = false;
    }
}
