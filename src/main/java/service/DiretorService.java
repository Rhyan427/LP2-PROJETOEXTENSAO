package service;

import dataTransfer.DiretorData;
import entity.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DiretorRepo;

import java.time.LocalDate;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepo repository;

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

    public Diretor buscarDiretor(Integer id){
        return repository.findById(id).orElse(null);
    }
}
