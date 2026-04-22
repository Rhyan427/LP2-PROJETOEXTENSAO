package service;

import entity.Discente;
import entity.DiscenteDiretor;
import entity.Grupo;
import entity.Papel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class DiscenteDiretorService {
    ArrayList<DiscenteDiretor> diretores = new ArrayList<>();

    public boolean diretorExiste(String nomeDir, String nomeGp) {
        for (DiscenteDiretor dir : diretores) {
            if ((Objects.equals(dir.getNome(), nomeDir) == true) && (Objects.equals(dir.getGrupo().getNome(), nomeGp) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean criarDiretor(String nomeDir, String nomeGp, Discente di, Grupo gp) {
        if (!diretorExiste(nomeDir, nomeGp)) {
            DiscenteDiretor dir = new DiscenteDiretor();
            dir.setNome(di.getNome());
            dir.setEmail(di.getEmail());
            dir.setSenha(di.getSenha());
            dir.setMatricula(di.getMatricula());
            dir.setSemestre(di.getSemestre());
            dir.setCurso(di.getCurso());
            dir.setCargo("Diretor do grupo " + gp.getNome());
            dir.setPapel(new Papel("Discente Diretor"));
            dir.setGrupo(gp);
            LocalDate a = LocalDate.now();
            dir.setDataInicio(a);
            dir.setDataFim(a.plusDays(30));
            diretores.add(dir);
            return true;
        }
        return false;
    }

    public boolean loginDiretor(DiscenteDiretor in, String nome, String senha) {
        for (DiscenteDiretor dir : diretores) {
            if ((Objects.equals(dir.getNome(), nome) == true) && (Objects.equals(dir.getSenha(), senha) == true)) {
                repasseDadosLogin(in, dir);
                return true;
            }
        }
        return false;
    }
    public void repasseDadosLogin(DiscenteDiretor in, DiscenteDiretor dir) {
        in.setNome(dir.getNome());
        in.setEmail(dir.getEmail());
        in.setSenha(dir.getSenha());
        in.setAtivo(dir.isAtivo());
        in.setPapel(dir.getPapel());
        in.setMatricula(dir.getMatricula());
        in.setSemestre(dir.getSemestre());
        in.setCurso(dir.getCurso());
        in.setCargo(dir.getCargo());
        in.setGrupo(dir.getGrupo());
        in.setDataInicio(dir.getDataInicio());
        in.setDataFim(dir.getDataFim());
    }
}
