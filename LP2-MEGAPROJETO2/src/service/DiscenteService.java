package service;

import dataTransfer.DiscenteData;
import entity.Discente;

public class DiscenteService {
    public Discente criarDiscente(DiscenteData dt) {
        Discente novo = new Discente(dt.getNome(),
                dt.getEmail(),
                dt.getSenha(),
                dt.getMatricula(),
                dt.getSemestre(),
                dt.getCurso());
        return novo;
    }


}
