package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;

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
        return repository.save(op);
    }

    public Oportunidade buscarPorId(Integer id){
        return repository.findById(id).orElse(null);
    }
}
