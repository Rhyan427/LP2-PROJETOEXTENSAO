package service;

import dataTransfer.OportData;
import entity.Oportunidade;

public class OportunidadeService {
    public Oportunidade criarOportunidade(OportData data) {
        Oportunidade op = new Oportunidade(data.getTitulo(),
                data.getDescricao(),
                data.getTipo(),
                data.getModalidade(),
                data.getCargaHoraria(),
                data.getVagas(),
                data.getDataPlanoAtividades(),
                data.getInicio(),
                data.getFim(),
                data.getAutor(),
                data.getResponsavel());
        return op;
    }
}
