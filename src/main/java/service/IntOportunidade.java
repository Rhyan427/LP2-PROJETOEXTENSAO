package service;

import dataTransfer.OportData;
import entity.Docente;
import entity.Oportunidade;
import entity.StatusOportunidade;
import entity.Usuario;

public interface IntOportunidade {
    Oportunidade criarOportunidade(Usuario u, Docente d, OportData data);
    boolean publicar(Oportunidade op, Usuario u, StatusOportunidade status);
    boolean fecharInscricoes(Oportunidade op, Usuario u);
    boolean encerrarOportunidade(Oportunidade op, Usuario u);
    boolean editarPlano(Oportunidade op, Usuario u, String novoPlano);
}
