package com.projetoextensao.lp2projetoextensaospring.service;

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

    public boolean publicarAviso(Usuario autor, String titulo, String mensagem) {
        if (autor == null || titulo == null || mensagem == null) {
            System.out.println("Erro! Dados invalidos para publicar novo aviso"); //TODO: mover para Front
            return false;
        }

        if (autor instanceof Docente || autor instanceof Diretor) {
            Aviso novo = new Aviso(titulo, mensagem, autor);
            System.out.println("Aviso publicado por " + autor.getNome()); //TODO: mover para Front
            repository.save(novo);
            return true;
        } else {
            System.out.println("Acesso negado! Apenas docentes e discentes diretores podem publicar avisos."); //TODO: mover para Front
            return false;
        }
    }
}
