package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DiscenteData;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.DiscenteRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepo repository;

    /**
     *
     * @param dt recebe o discente informado pelo controller
     * @return o discente criado e salvo no repositório
     */
    public Discente criarDiscente(DiscenteData dt) {
        Discente novo = new Discente(dt.getNome(),
                dt.getEmail(),
                dt.getSenha(),
                dt.getMatricula(),
                dt.getSemestre(),
                dt.getCurso());
        return repository.save(novo);
    }

    /**
     *
     * @param matricula recebe a matricula informada pelo usuário para buscar um aluno no banco
     * @return o usuário achado por aquela matricula e, caso não tenha achado, retorna null
     */
    public Discente buscarPorMatricula(String matricula){
        return repository.findByMatricula(matricula).orElse(null);
    }

    /**
     *
     * @return todos os Discentes no banco de dados
     */
    public List<Discente> listarTodos(){
        return repository.findAll();
    }

    /**
     *
     * @param id recebe o id para buscar um discente no banco
     * @return o usuario correspondente ao ID especificado
     */
    public Optional<Discente> buscarPorId(Integer id){
        return repository.findById(id);
    }

}
