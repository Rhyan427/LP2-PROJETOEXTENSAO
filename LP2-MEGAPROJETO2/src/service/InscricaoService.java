package service;

import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class InscricaoService {
    ArrayList<Inscricao> inscricoes = new ArrayList<>();

    public void verInscricoes() {
        for (Inscricao ins : inscricoes) {
            System.out.printf("%s %s, solicitada por %s. Status: %s", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getDiscente().getNome(), ins.getStatus());
        }
    }

    public void verInscricoesPorDiscente(Discente di) {
        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getDiscente(), di) == true) {
                System.out.printf("- %s %s. Status: %s\n", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getStatus());
            }
        }
    }

    public void verInscricoesPendentes() {
        for (Inscricao ins : inscricoes) {
            if (ins.getStatus() == StatusInscricao.PENDENTE) {
                System.out.printf("- %s %s. Status: %s. Autor: %s\n", ins.getOportunidade().getTipo(), ins.getOportunidade().getTitulo(), ins.getStatus(), ins.getDiscente().getNome());
            }
        }
    }

    public boolean fazerInscricao(Discente di, Oportunidade oportunidade) {
        if (!inscricaoExiste(di, oportunidade) && oportunidade.getStatus() == StatusOportunidade.PUBLICADA) {
            Inscricao inscricao = new Inscricao(di, oportunidade, StatusInscricao.PENDENTE, LocalDate.now());
            inscricoes.add(inscricao);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean inscricaoExiste(Discente di, Oportunidade oportunidade) {
        for (Inscricao ins : inscricoes) {
            if ((Objects.equals(ins.getDiscente(), di) == true) && (Objects.equals(ins.getOportunidade(), oportunidade) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean analisarInscricao(String tituloOp, String nomeDi, StatusInscricao status) {
        for (Inscricao ins : inscricoes) {
            if ((Objects.equals(ins.getDiscente().getNome(), nomeDi) == true) && (Objects.equals(ins.getOportunidade().getTitulo(), tituloOp) == true)) {
                ins.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean cancelarInscricao(Discente di, String titulo) {
        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getDiscente(), di) == true && Objects.equals(ins.getOportunidade().getTitulo(), titulo) == true) {
                if (ins.getOportunidade().getStatus() == StatusOportunidade.PUBLICADA && (ins.getStatus() == StatusInscricao.APROVADO || ins.getStatus() == StatusInscricao.PENDENTE)) {
                    ins.setStatus(StatusInscricao.CANCELADO);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Inscricao> pegarInscricoesPorOportunidade(Oportunidade op) {
        ArrayList<Inscricao> recuperadas = new ArrayList<>();
        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getOportunidade(), op)) {
                recuperadas.add(ins);
            }
        }
        return recuperadas;
    }
}
