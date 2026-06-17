package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.AprovtData;
import com.projetoextensao.lp2projetoextensaospring.entity.Aproveitamento;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusAproveitamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.AproveitamentoRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class AproveitamentoService {

    @Autowired
    private AproveitamentoRepo repository;

    public Aproveitamento criarAproveitamento(AprovtData data) {
        Aproveitamento novo = new Aproveitamento(
                data.getDiscente(),
                data.getDescricao(),
                data.getHoras(),
                StatusAproveitamento.PENDENTE,
                data.getCertificado());
        return repository.save(novo);
    }

    public int calcularHorasAprovadas(Discente discente, List<Aproveitamento> todosAproveitamentos) {
        return todosAproveitamentos.stream()
                .filter(aproveitamento -> aproveitamento.getDiscente().getMatricula().equals(discente.getMatricula()))
                .filter(aproveitamento -> aproveitamento.getStatus() == StatusAproveitamento.APROVADO)
                .mapToInt(Aproveitamento::getHoras)
                .sum();
    }

    // RF022 — coordenador indefere: registra motivo e abre prazo de 5 dias para reenvio
    public boolean indeferir(Aproveitamento aprov, String motivo) {
        if (aprov == null) return false;

        if (aprov.getStatus() != StatusAproveitamento.PENDENTE) {
            System.out.println("Apenas solicitações PENDENTES podem ser indeferidas.");
            return false;
        }
        if (aprov.getDataLimiteDecisao() != null && LocalDate.now().isAfter(aprov.getDataLimiteDecisao())) {
            System.out.println("AVISO: O prazo de 10 dias para decisão foi ultrapassado.");
        }
        aprov.setStatus(StatusAproveitamento.INDEFERIDO);
        aprov.setMotivo_rejeicao(motivo);
        aprov.setDataLimiteReenvio(LocalDate.now().plusDays(5)); // RF022

        repository.save(aprov);
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

        repository.save(aprov);
        return true;
    }
}
