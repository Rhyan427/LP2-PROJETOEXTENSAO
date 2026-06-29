package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DiretorData;
import com.projetoextensao.lp2projetoextensaospring.entity.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.DiretorRepo;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepo repository;

    /**
     *
     * @param data recebe os dados de cadastro do novo diretor
     * @return o novo usuário salvo no banco de dados
     */

    public Diretor criarDiretor(DiretorData data) {
        LocalDate inicio = LocalDate.now();
        Diretor dir = new Diretor(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getMatricula(),
                data.getSemestre(),
                data.getCurso(),
                data.getGrupo(),
                "Diretor do grupo " + data.getGrupo().getNome(),
                inicio,
                inicio.plusDays(30));
        return repository.save(dir);
    }

    /**
     *
     * @param id recebe o id do diretor a ser buscado
     * @return os dados do diretor correspondente, ou se não for encontrado, retornna "null"
     */

    public Optional<Diretor> buscarDiretor(Integer id){
        return repository.findById(id);
    }
}
