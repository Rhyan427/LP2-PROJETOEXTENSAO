package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusOportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;

public interface IntOportunidade {
    Oportunidade criarOportunidade(Usuario u, Docente d, OportData data);
    boolean publicar(Oportunidade op, Usuario u, StatusOportunidade status);
    boolean fecharInscricoes(Oportunidade op, Usuario u);
    boolean encerrarOportunidade(Oportunidade op, Usuario u);
    boolean editarPlano(Oportunidade op, Usuario u, String novoPlano);
}
