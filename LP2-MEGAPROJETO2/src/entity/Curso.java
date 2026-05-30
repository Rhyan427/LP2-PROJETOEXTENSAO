package entity;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private PPC ppcAtual;
    private ArrayList<Discente> alunos;
    private ArrayList<PPC> historico;

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
    public PPC getPpcAtual() {
        return ppcAtual;
    }
    public void setPpcAtual(PPC ppcAtual) {
        this.ppcAtual = ppcAtual;
    }
    public ArrayList<Discente> getAlunos() {
        return alunos;
    }
    public void setAlunos(ArrayList<Discente> alunos) {
        this.alunos = alunos;
    }
    public ArrayList<PPC> getHistorico() {
        return historico;
    }
    public void setHistorico(ArrayList<PPC> historico) {
        this.historico = historico;
    }

    public Curso(String nome, int codigo, int cargaHoraria, PPC ppcAtual) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.ppcAtual = ppcAtual;
        this.alunos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }
}
