package entity;

public class Docente extends Usuario {
    private String siape;
    private String departamento;

    public String getSiape() {
        return siape;
    }
    public void setSiape(String siape) {
        this.siape = siape;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Docente(String nome, String email, String senha, String siape, String departamento) {
        super(nome, email, senha, new Papel("Docente"));
        this.siape = siape;
        this.departamento = departamento;
    }

    public Docente() {} //construtor vazio para login
}
