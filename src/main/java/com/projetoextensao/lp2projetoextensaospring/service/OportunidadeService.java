package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepo repository;

    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        Oportunidade op = new Oportunidade(
                data.getTitulo(),
                data.getDescricao(),
                data.getTipo(),
                data.getModalidade(),
                data.getCargaHoraria(),
                data.getVagas(),
                data.getDataPlanoAtividades(),
                data.getInicio(),
                data.getFim(),
                u,
                d
        );
        if(data.getDataPlanoAtividades() != null){
            op.setDataPlanoAtividades(data.getDataPlanoAtividades());
        } else {
            op.setDataPlanoAtividades(LocalDate.now());
        }
        return repository.save(op);
    }

    //TODO: movido esse método de inscricaoService pra cá. Adaptar para seguir RF017
    /*
    public boolean substituirParticipante(Oportunidade op, Discente in, Discente out, String justificativa  ){

        Optional<Inscricao> optSaindo = repository.findByOportunidadeAndDiscente(op, out);
        Optional<Inscricao> optEntrando = repository.findByOportunidadeAndDiscente(op, in);


        if (optSaindo.isPresent() && optEntrando.isPresent()) {
            Inscricao inscricaoSaindo = optSaindo.get();
            Inscricao inscricaoEntrando = optEntrando.get();

            if (inscricaoSaindo.getStatus() == StatusInscricao.APROVADO &&
                    inscricaoEntrando.getStatus() == StatusInscricao.PENDENTE) {

                inscricaoSaindo.setStatus(StatusInscricao.CANCELADO);
                inscricaoSaindo.setJustificativa(justificativa);

                inscricaoEntrando.setStatus(StatusInscricao.APROVADO);

                repository.save(inscricaoSaindo);
                repository.save(inscricaoEntrando);
                return true;
            }
        }
        return false;
    }
    */
    public Optional<Oportunidade> buscarPorId(Integer id){
        return repository.findById(id);
    }
}
