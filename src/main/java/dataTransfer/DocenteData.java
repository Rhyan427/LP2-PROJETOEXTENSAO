package dataTransfer;

import entity.Docente;
import entity.Papel;

import java.util.Objects;

public class DocenteData {
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private Papel papel;
    private String siape;
    private String departamento;

    public DocenteData(String nome, String email, String senha, boolean ativo, Papel papel, String siape, String departamento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.papel = papel;
        this.siape = siape;
        this.departamento = departamento;
    }

    public DocenteData(Docente docente) {
        this.nome = docente.getNome();
        this.email = docente.getEmail();
        this.senha = docente.getSenha();
        this.ativo = docente.isAtivo();
        this.papel = docente.getPapel();
        this.siape = docente.getSiape();
        this.departamento = docente.getDepartamento();
    }

    public DocenteData() {}


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Papel getPapel() { return papel; }
    public void setPapel(Papel papel) { this.papel = papel; }
    public String getSiape() { return siape; }
    public void setSiape(String siape) { this.siape = siape; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DocenteData that = (DocenteData) o;
        return ativo == that.ativo && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && Objects.equals(papel, that.papel) && Objects.equals(siape, that.siape) && Objects.equals(departamento, that.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, senha, ativo, papel, siape, departamento);
    }
}
