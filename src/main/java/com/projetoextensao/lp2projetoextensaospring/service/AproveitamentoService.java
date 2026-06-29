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
import java.util.Optional;

@Service
public class AproveitamentoService {

    @Autowired
    private AproveitamentoRepo repository;

    /**
     *
     * @param data converte os dados JSON para AprovtData
     * @return o aproveitamento criado e salvo no repositório
     */

    public Aproveitamento criarAproveitamento(AprovtData data) {
        Aproveitamento novo = new Aproveitamento(
                data.getDiscente(),
                data.getDescricao(),
                data.getHoras(),
                StatusAproveitamento.PENDENTE,
                data.getCertificado());
        return repository.save(novo);
    }

    /**
     *
     * @param discente recebe o discente para o cálculo
     * @param todosAproveitamentos lista de todos os aproveitamentos para filtrar
     * @return o total de horas aprovadas para aquele dicente
     */

    public int calcularHorasAprovadas(Discente discente, List<Aproveitamento> todosAproveitamentos) {
        return todosAproveitamentos.stream()
                .filter(aproveitamento -> aproveitamento.getDiscente().getMatricula().equals(discente.getMatricula()))
                .filter(aproveitamento -> aproveitamento.getStatus() == StatusAproveitamento.APROVADO)
                .mapToInt(Aproveitamento::getHoras)
                .sum();
    }

    /**
     * RF022 — coordenador indefere: registra motivo e abre prazo de 5 dias para reenvio
     * @param aprov recebe o aproveitamento que será indeferido
     * @param motivo a string contendo a justificativa do indeferimento
     * @return true se o indeferimento for bem sucedido, se não, false
     */
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

    /**
     * @param aprov recebe o aproveitamento em questão
     * @param novaDescricao recebe a nova descrição que irá substituir a antiga
     * @param novasHoras recebe a nova quantidade de horas
     * @return true se o aproveitamento puder ser reenviado, se não, false
     */

    public boolean reenviar(Aproveitamento aprov, String novaDescricao, Integer novasHoras) {
        if (aprov == null) return false;

        Aproveitamento aproveitamento = new Aproveitamento();
        if (aprov.getStatus() != StatusAproveitamento.INDEFERIDO) {
            System.out.println("Esta solicitação não está indeferida.");
            return false;
        }
        if (aprov.getDataLimiteReenvio() == null || LocalDate.now().isAfter(aprov.getDataLimiteReenvio())) {
            System.out.println("O prazo de 5 dias para reenvio foi ultrapassado.");
            return false;
        }
        aproveitamento.setDescricao(novaDescricao);
        aproveitamento.setHoras(novasHoras);
        aproveitamento.setStatus(StatusAproveitamento.PENDENTE);
        aproveitamento.setMotivo_rejeicao("N/A");
        aproveitamento.setDataLimiteReenvio(null);

        repository.save(aproveitamento);
        return true;
    }

    public List <Aproveitamento> listarTodos (){
        return repository.findAll();
    }

    public Optional <Aproveitamento> buscarPorId(Integer id){
        return repository.findById(id);
    }
}
