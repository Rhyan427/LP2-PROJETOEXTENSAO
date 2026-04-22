package service;

import entity.Coordenador;
import entity.Curso;
import entity.Discente;
import java.util.ArrayList;
import java.util.Objects;

public class CursoService {
    ArrayList<Curso> cursos = new ArrayList<>();
    ArrayList<Curso> historico = new ArrayList<>();

    public boolean atualizarPPC(Curso curso, int novoCodigo, int novaCargaHoraria, String novaVersao, Coordenador autor) {
        //se o curso e o docente existirem, ele substitui a carga horaria e a PPC pelas novas
        if (curso != null && autor != null) {
            historico.add(curso);
            curso.setCargaHoraria(novaCargaHoraria);
            curso.setPpcVersao(novaVersao);
            return true;
        }
        return false;
    }

    /*public ArrayList<Discente> listarAlunos(Curso curso){
        //cria duas arrays para poder filtrar os alunos ativos e inativos em cada uma
        ArrayList<Discente> alunosAtivos = new ArrayList<>();
        ArrayList<Discente> alunosInativos = new ArrayList<>();

        //checa se o curso ou se tem alunos nele existe
        if(curso == null || curso.getAlunos() == null){
            System.out.println("Erro! Dados invalidos.");
            return new ArrayList<>();
        }

        for (Discente aluno : curso.getAlunos()) {
            if (aluno.isStatus()) {
                alunosAtivos.add(aluno); //se for o status for true, guarda na array de alunos ativos
            } else {
                alunosInativos.add(aluno); //se false, vai para a array de alunos inativos
            }
        }

        //mescla as arrays para exibição
        ArrayList<Discente> todosAlunos = new ArrayList<>(alunosAtivos);
        todosAlunos.addAll(alunosInativos);

        return todosAlunos;
    }*/

    public Curso pegarCurso(String nome) {
        for (Curso curso : cursos) {
            if (Objects.equals(curso.getNome(), nome)) {
                return curso;
            }
        }
        return null;
    }
}
