package service;

import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class OportunidadeService {
    public ArrayList<Oportunidade> oportunidades = new ArrayList<>();
    DocenteService docenteService = new DocenteService();

    public Oportunidade criarOportunidade(Docente responsavel, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        if (responsavel != null) {
            Oportunidade op = docenteService.criarOportunidade(responsavel, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, inicio, fim);
            if (op != null) {
                oportunidades.add(op);
                return op;
            }
        }
        return null;
    }
    public Oportunidade criarOportunidadeDir(Docente responsavel, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim) {
        if (responsavel != null) {
            Oportunidade op = docenteService.criarOportunidade(responsavel, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, inicio, fim);
            if (op != null) {
                op.setStatus(StatusOportunidade.AGUARDANDO_APROVACAO);
                oportunidades.add(op);
                return op;
            }
        }
        return null;
    } //TODO: funciona, mas é melhor fundir com o método de cima

    public void verOportunidades() {
        for (Oportunidade op : oportunidades) {
            if (op.getStatus() != StatusOportunidade.AGUARDANDO_APROVACAO || op.getStatus() != StatusOportunidade.RASCUNHO) {
                System.out.printf("- Título: %s. %s, %s, com %d vagas. %d horas ofertadas. Status: %s\n", op.getTitulo(), op.getTipo(), op.getModalidade(), op.getVagas(), op.getCargaHoraria(), op.getStatus());
            }
        }
    }
    public void verOportunidadesPendentes() {
        for (Oportunidade op : oportunidades) {
            if (op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO || op.getStatus() == StatusOportunidade.RASCUNHO) {
                System.out.printf("- Título: %s. %s, %s, com %d vagas. %d horas ofertadas. Status: %s\n", op.getTitulo(), op.getTipo(), op.getModalidade(), op.getVagas(), op.getCargaHoraria(), op.getStatus());
            }
        }
    }

    public Oportunidade pegarOportunidade(String titulo) {
        for (Oportunidade op : oportunidades) {
            if (Objects.equals(op.getTitulo(), titulo)) {
                return op;
            }
        }
        return null;
    }

    //Publica ou cancela a oportunidade solicitada.
    public boolean publicar(Oportunidade op, StatusOportunidade status) {
        if (op.getStatus() == StatusOportunidade.RASCUNHO || op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
            op.setStatus(status);
            return true;
        } else {
            return false;
        }
    }

    // Encerra o período de inscrições e muda o status para EM_PROGRESSO.
    public boolean fecharInscricoes(Oportunidade op) {
        if (op == null) {
            return false;
        }
        if (op.getStatus() == StatusOportunidade.PUBLICADA) {
            op.setStatus(StatusOportunidade.EM_PROGRESSO);
            return true;
        } else {
            return false;
        }
    }

    // Encerra a oportunidade
    public boolean encerrarOportunidade(Oportunidade op) {
        if (op == null) {
            return false;
        }
        if (op.getStatus() == StatusOportunidade.EM_PROGRESSO) {
            op.setStatus(StatusOportunidade.ENCERRADA);
            return true;
        } else {
            return false;
        }
    }

    public boolean editarPlano(Oportunidade op, String novoPlano) {
        if (op == null) {
            return false;
        }
        op.setPlano(novoPlano);
        return true;
    }
}
