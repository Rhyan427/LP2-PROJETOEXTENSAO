package entity;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private String ppcVersao;
    private ArrayList<Discente> alunos;


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public String getPpcVersao() {
        return ppcVersao;
    }
    public void setPpcVersao(String ppcVersao) {
        this.ppcVersao = ppcVersao;
    }
    public ArrayList<Discente> getAlunos() {
        return alunos;
    }
    public void setAlunos(ArrayList<Discente> alunos) {
        this.alunos = alunos;
    }

    public Curso(String nome, int codigo, int cargaHoraria, String ppcVersao) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.ppcVersao = ppcVersao;
        this.alunos = new ArrayList<>();
    }
}
