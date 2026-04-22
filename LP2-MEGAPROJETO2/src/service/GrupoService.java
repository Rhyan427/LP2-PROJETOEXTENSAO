package service;

import entity.Curso;
import entity.Discente;
import entity.Docente;
import entity.Grupo;
import java.util.ArrayList;
import java.util.Objects;

public class GrupoService {
    ArrayList<Grupo> grupos = new ArrayList<>();

    public void verGrupos() {
        System.out.println("Lista de grupos: ");
        for (Grupo grupo : grupos) {
            System.out.printf("- Grupo %s. Responsável: %s. Membros: ", grupo.getNome(), grupo.getResponsavel().getNome());
            for (Discente di : grupo.getMembros()) {
                System.out.printf("%s ", di.getNome());
            }
            System.out.print("\n");
        }
    }

    public boolean grupoExiste(String nome, String email) {
        for (Grupo grupo : grupos) {
            if ((Objects.equals(grupo.getEmail(), email) == true) || (Objects.equals(grupo.getNome(), nome) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean criarGrupo(String nome, String descricao, String email, Docente responsavel) {
        if (!grupoExiste(nome, email) && responsavel != null) {
            ArrayList<Discente> membros = new ArrayList<>();
            Grupo novo = new Grupo(nome, descricao, email, responsavel, membros);
            grupos.add(novo);
            return true;
        }
        return false;
    }

    public boolean adicionarMembros(Discente di, String nomeGp) {
        for (Grupo grupo : grupos) {
            if (Objects.equals(grupo.getNome(), nomeGp) == true && di != null) {
                if (!membroPresente(di, grupo)) {
                    grupo.getMembros().add(di);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removerMembros(Discente di, String nomeGp) {
        for (Grupo grupo : grupos) {
            if (Objects.equals(grupo.getNome(), nomeGp) == true && di != null) {
                if (membroPresente(di, grupo)) {
                    grupo.getMembros().remove(di);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean membroPresente(Discente di, Grupo grupo) {
        for (Discente a : grupo.getMembros()) {
            if (Objects.equals(a, di) == true) {
                return true;
            }
        }
        return false;
    }

    public Grupo pegarGrupo(String nome) {
        for (Grupo grupo : grupos) {
            if (Objects.equals(grupo.getNome(), nome)) {
                return grupo;
            }
        }
        return null;
    }
}
