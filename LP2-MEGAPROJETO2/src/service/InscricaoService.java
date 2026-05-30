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
            if (Objects.equals(ins.getDiscente(), di)) {
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

    public Inscricao fazerInscricao(Discente di, Oportunidade oportunidade) {
        if (!inscricaoExiste(di, oportunidade) && oportunidade.getStatus() == StatusOportunidade.PUBLICADA) {
            Inscricao inscricao = new Inscricao(di, oportunidade, StatusInscricao.PENDENTE, LocalDate.now());
            inscricoes.add(inscricao);

            return inscricao;
        }
        else {
            return null;
        }
    }
    public boolean inscricaoExiste(Discente di, Oportunidade oportunidade) {
        for (Inscricao ins : inscricoes) {
            if ((Objects.equals(ins.getDiscente(), di)) && (Objects.equals(ins.getOportunidade(), oportunidade))) {
                return true;
            }
        }
        return false;
    }

    public boolean analisarInscricao(String tituloOp, String nomeDi, StatusInscricao status) {
        for (Inscricao ins : inscricoes) {
            if ((Objects.equals(ins.getDiscente().getNome(), nomeDi)) && (Objects.equals(ins.getOportunidade().getTitulo(), tituloOp))) {
                ins.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean cancelarInscricao(Discente di, String titulo) {
        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getDiscente(), di) && Objects.equals(ins.getOportunidade().getTitulo(), titulo)) {
                if (ins.getOportunidade().getStatus() == StatusOportunidade.PUBLICADA && (ins.getStatus() == StatusInscricao.APROVADO || ins.getStatus() == StatusInscricao.PENDENTE)) {
                    ins.setStatus(StatusInscricao.CANCELADO);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean substituirParticipante(Oportunidade op, Discente in, Discente out, String justificativa  ){

        Inscricao inscricaoSaindo = null;
        Inscricao inscricaoEntrando = null;


        for (Inscricao ins : inscricoes) {
            if (Objects.equals(ins.getOportunidade(), op)) {
                if (Objects.equals(ins.getDiscente(), in)) {
                    inscricaoSaindo = ins;
                } else if (Objects.equals(ins.getDiscente(), in)) {
                    inscricaoEntrando = ins;
                }
            }
        }

        if (inscricaoSaindo != null && inscricaoEntrando != null) {
            if (inscricaoSaindo.getStatus() == StatusInscricao.APROVADO && inscricaoEntrando.getStatus() == StatusInscricao.PENDENTE) {

                inscricaoSaindo.setStatus(StatusInscricao.CANCELADO);
                inscricaoSaindo.setJustificativa(justificativa);

                inscricaoEntrando.setStatus(StatusInscricao.APROVADO);

                return true;
            }
        }
        return false;
    }
}
