package entity;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private Papel papel;

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

    public Usuario(String nome, String email, String senha, Papel papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = true;
    }
    public Usuario() {} //construtor vazio para login
}
