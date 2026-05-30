package service;

import dataTransfer.DiretorData;
import entity.Diretor;

import java.time.LocalDate;

public class DiretorService {

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
        return dir;
    }
}
