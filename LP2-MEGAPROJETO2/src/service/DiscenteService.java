package service;

import entity.Curso;
import entity.Discente;
import java.util.ArrayList;
import java.util.Objects;

public class DiscenteService {
    ArrayList<Discente> discentes = new ArrayList<>();

    public boolean discenteExiste(String nome, String email, String matricula) {
        for (Discente di : discentes) {
            if ((Objects.equals(di.getEmail(), email) == true) || (Objects.equals(di.getNome(), nome) == true) || (Objects.equals(di.getMatricula(), matricula) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean criarDiscente(String nome, String email, String senha, String matricula, int semestre, Curso curso) {
        if (!discenteExiste(nome, email, matricula)) {
            Discente novo = new Discente(nome, email, senha, matricula, semestre, curso);
            discentes.add(novo);
            return true;
        }
        return false;
    }

    public boolean loginDiscente(Discente in, String nome, String senha) {
        for (Discente di : discentes) {
            if ((Objects.equals(di.getNome(), nome) == true) && (Objects.equals(di.getSenha(), senha) == true)) {
                repasseDadosLogin(in, di);
                return true;
            }
        }
        return false;
    }
    public void repasseDadosLogin(Discente in, Discente di) {
        in.setNome(di.getNome());
        in.setEmail(di.getEmail());
        in.setSenha(di.getSenha());
        in.setAtivo(di.isAtivo());
        in.setPapel(di.getPapel());
        in.setMatricula(di.getMatricula());
        in.setSemestre(di.getSemestre());
        in.setCurso(di.getCurso());
    }

    public void verCurso(Discente di) {
        Curso curso = di.getCurso();
        System.out.println(curso.getNome());
        System.out.println(curso.getCargaHoraria());
    }

    public void mudarCurso(Discente di, Curso curso) {
        if (curso != null) {
            di.setCurso(curso);
        }
    }

    public Discente pegarDiscente(String nome) {
        for (Discente di : discentes) {
            if (Objects.equals(di.getNome(), nome) == true) {
                return di;
            }
        }
        return null;
    }
}
