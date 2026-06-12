package service;

import dataTransfer.DiscenteData;
import entity.Discente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DiscenteRepo;

@Service
public class DiscenteService {

    @Autowired
    private DiscenteRepo repository;

    public Discente criarDiscente(DiscenteData dt) {
        Discente novo = new Discente(dt.getNome(),
                dt.getEmail(),
                dt.getSenha(),
                dt.getMatricula(),
                dt.getSemestre(),
                dt.getCurso());
        return repository.save(novo);
    }

    public Discente buscarPorMatricula(String matricula){
        return repository.findByMatricula(matricula).orElse(null);
    }

}
