package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.SubstituicaoData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.repository.DiscenteRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.InscricaoRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepo repository;

    @Autowired
    private InscricaoRepo inscricaoRepo; //novo

    @Autowired
    private DiscenteRepo discenteRepo; //novo

    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        // ... (Mantenha o seu código de criar oportunidade exatamente como estava)
        Oportunidade op = new Oportunidade(
                data.getTitulo(), data.getDescricao(), data.getTipo(), data.getModalidade(),
                data.getCargaHoraria(), data.getVagas(), data.getDataPlanoAtividades(),
                data.getInicio(), data.getFim(), u, d
        );
        if(data.getDataPlanoAtividades() != null){
            op.setDataPlanoAtividades(data.getDataPlanoAtividades());
        } else {
            op.setDataPlanoAtividades(LocalDate.now());
        }
        return repository.save(op);
    }

    public Optional<Oportunidade> buscarPorId(Integer id){
        return repository.findById(id);
    }


    public boolean substituirParticipante(Integer idOportunidade, SubstituicaoData data) {

        Optional<Oportunidade> opOpt = repository.findById(idOportunidade);
        Optional<Discente> outOpt = discenteRepo.findById(data.getIdDiscenteSaindo());
        Optional<Discente> inOpt = discenteRepo.findById(data.getIdDiscenteEntrando());

        if (opOpt.isEmpty() || outOpt.isEmpty() || inOpt.isEmpty()) {
            return false;
        }

        Optional<Inscricao> optSaindo = inscricaoRepo.findByOportunidadeAndDiscente(opOpt.get(), outOpt.get());
        Optional<Inscricao> optEntrando = inscricaoRepo.findByOportunidadeAndDiscente(opOpt.get(), inOpt.get());


        if (optSaindo.isPresent() && optEntrando.isPresent()) {
            Inscricao inscricaoSaindo = optSaindo.get();
            Inscricao inscricaoEntrando = optEntrando.get();

            if (inscricaoSaindo.getStatus() == StatusInscricao.APROVADO &&
                    inscricaoEntrando.getStatus() == StatusInscricao.PENDENTE) {

                inscricaoSaindo.setStatus(StatusInscricao.CANCELADO);
                inscricaoSaindo.setJustificativa(data.getJustificativa());

                inscricaoEntrando.setStatus(StatusInscricao.APROVADO);

                inscricaoRepo.save(inscricaoSaindo);
                inscricaoRepo.save(inscricaoEntrando);
                return true;
            }
        }
        return false;
    }
}