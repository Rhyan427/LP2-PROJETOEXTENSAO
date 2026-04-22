package entity;

public class Coordenador extends Usuario {
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

    public Coordenador(String nome, String email, String senha, String siape, String departamento) {
        super(nome, email, senha, new Papel("Coordenador"));
        this.siape = siape;
        this.departamento = departamento;
    }
    public Coordenador() {} //construtor vazio para login
}
