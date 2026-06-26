package com.projetoextensao.lp2projetoextensaospring.service;


import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.UsuarioRepo;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo repository;

    public void desativarConta(Usuario usuario){
        if(usuario != null){
            usuario.setAtivo(false);
            repository.save(usuario);
        }
    }

    public void anonimizarConta(Usuario usuario){
        if(usuario != null){
            usuario.anonimizar();
            repository.save(usuario);
        }
    }

    public Usuario autenticar(String email, String senha){
        Optional<Usuario> optUsuario = repository.findByEmail(email);

        if(optUsuario.isPresent()){
            Usuario u = optUsuario.get();

            if(u.getSenha().equals(senha)){
                if(!u.isAtivo()){
                    System.out.println("\n[BLOQUEADO] Esta conta está desativada ou foi anonimizada");
                    return null;
                }
                return u;
            }
        }
        return null;
    }

    public Optional<Usuario> buscarPorId(Integer id){
        return repository.findById(id);
    }
}
