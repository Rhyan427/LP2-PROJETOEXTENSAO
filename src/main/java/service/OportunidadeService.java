package service;

import dataTransfer.OportData;
import entity.Docente;
import entity.Oportunidade;
import entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OportunidadeRepo;

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
