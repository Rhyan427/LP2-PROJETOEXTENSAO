package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.AvisoData;
import com.projetoextensao.lp2projetoextensaospring.entity.Aviso;
import com.projetoextensao.lp2projetoextensaospring.entity.Diretor;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.AvisoRepo;

import java.util.List;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepo repository;

    public List<Aviso> listarAvisos(){
        return repository.findAll();
    }

    /**
     * @param autor recebe o autor do aviso
     * @param
     * @param
     * @return true se o aviso puder ser cadastrado, se não, false
     */

    public boolean publicarAviso(Usuario autor, AvisoData data) {

        if (autor instanceof Docente || autor instanceof Diretor) {
            Aviso novo = new Aviso(data.getTitulo(), data.getMensagem(), autor);
            repository.save(novo);
            return true;
        } else {
            System.out.println("Acesso negado! Apenas docentes e discentes diretores podem publicar avisos.");
            return false;
        }
    }
}
