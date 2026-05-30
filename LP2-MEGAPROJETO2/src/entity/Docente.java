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

    public Docente(String nome, String email, String senha, Papel papel, String siape, String departamento) {
        super(nome, email, senha, papel);
        this.siape = siape;
        this.departamento = departamento;
    } //construtor para coordenador

    public Docente() {} //construtor vazio para login

    @Override
    public void anonimizar(){
        super.anonimizar();
        this.siape = "Anonimo -" + System.currentTimeMillis();
    }
}
