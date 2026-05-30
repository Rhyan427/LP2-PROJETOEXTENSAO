package entity;

public class Discente extends Usuario {
    private String matricula;
    private int semestre;
    private Curso curso;


    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Discente(String nome, String email, String senha, String matricula, int semestre, Curso curso) {
        super(nome, email, senha, new Papel("Discente"));
        this.matricula = matricula;
        this.semestre = semestre;
        this.curso = curso;
    }

    public Discente() {} //construtor vazio para login

    @Override
    public void anonimizar(){
        super.anonimizar();
        this.matricula = "ANONIMO-" + System.currentTimeMillis();
    }
}
