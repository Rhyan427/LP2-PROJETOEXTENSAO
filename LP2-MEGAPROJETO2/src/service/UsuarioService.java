package service;

import entity.Usuario;
import java.util.ArrayList;

public class UsuarioService {

    public void desativarConta(Usuario usuario){
        if(usuario != null){
            usuario.setAtivo(false);
        }
    }

    //TODO preservacao de do histórico para fins de auditoria faltando
    public void anonimizarConta(Usuario usuario){
        if(usuario != null){
            usuario.anonimizar();
        }
    }

    public Usuario autenticar(String email, String senha, ArrayList<Usuario> usuarios){
        for(Usuario u: usuarios){
            if(u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)){
                if(!u.isAtivo()){
                    System.out.println("\n[BLOQUEADO] Esta conta esta desativada ou foi anonimizada");
                    return null;
                }
                return u;
            }
        }
        return null;
    }
}
