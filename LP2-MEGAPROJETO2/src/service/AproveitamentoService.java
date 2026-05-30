package service;

import dataTransfer.AprovtData;
import entity.Aproveitamento;
import entity.StatusAproveitamento;
import entity.Discente;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AproveitamentoService {

    public Aproveitamento criarAproveitamento(AprovtData data) {
        Aproveitamento novo = new Aproveitamento(
                data.getDiscente(),
                data.getDescricao(),
                data.getHoras(),
                StatusAproveitamento.PENDENTE,
                data.getCertificado());
        return novo;
    }

    public int calcularHorasAprovadas(Discente discente, List<Aproveitamento> todosAproveitamentos) {
        int horas = 0;
        for (Aproveitamento aprov : todosAproveitamentos) {
            if (aprov.getDiscente().getMatricula().equals(discente.getMatricula())) {
                if (aprov.getStatus() == StatusAproveitamento.APROVADO) {
                    horas += aprov.getHoras();
                }
            }
        }
        return horas;
    }

    // RF022 — coordenador indefere: registra motivo e abre prazo de 5 dias para reenvio
    public boolean indeferir(Aproveitamento aprov, String motivo) {
        if (aprov == null) return false;
        if (aprov.getStatus() != StatusAproveitamento.PENDENTE) {
            System.out.println("Apenas solicitações PENDENTES podem ser indeferidas.");
            return false;
        }
        if (LocalDate.now().isAfter(aprov.getDataLimiteDecisao())) {
            System.out.println("AVISO: O prazo de 10 dias para decisão foi ultrapassado.");
        }
        aprov.setStatus(StatusAproveitamento.INDEFERIDO);
        aprov.setMotivo_rejeicao(motivo);
        aprov.setDataLimiteReenvio(LocalDate.now().plusDays(5)); // RF022
        return true;
    }

    // RF023 — discente reenvia solicitação indeferida dentro do prazo de 5 dias
    public boolean reenviar(Aproveitamento aprov, String novaDescricao, int novasHoras) {
        if (aprov == null) return false;
        if (aprov.getStatus() != StatusAproveitamento.INDEFERIDO) {
            System.out.println("Esta solicitação não está indeferida.");
            return false;
        }
        if (aprov.getDataLimiteReenvio() == null || LocalDate.now().isAfter(aprov.getDataLimiteReenvio())) {
            System.out.println("O prazo de 5 dias para reenvio foi ultrapassado.");
            return false;
        }
        aprov.setDescricao(novaDescricao);
        aprov.setHoras(novasHoras);
        aprov.setStatus(StatusAproveitamento.PENDENTE);
        aprov.setMotivo_rejeicao("N/A");
        aprov.setDataLimiteReenvio(null);
        return true;
    }
}
