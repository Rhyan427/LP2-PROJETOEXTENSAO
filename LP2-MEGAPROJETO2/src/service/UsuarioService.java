package service;

import entity.Papel;
import entity.Usuario;
import java.util.ArrayList;
import java.util.Objects;

public class UsuarioService {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    OportunidadeService oportunidadeService = new OportunidadeService();

    public boolean usuarioExiste(String nome, String email) {
        for (Usuario u : usuarios) {
            if ((Objects.equals(u.getEmail(), email) == true) || (Objects.equals(u.getNome(), nome) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean criarConta(String nome, String email, String senha) {
        if (!usuarioExiste(nome, email)) {
            Usuario novo = new Usuario(nome, email, senha, new Papel("Visitante"));
            usuarios.add(novo);
            return true;
        }
        return false;
    }

    public boolean loginUsuario(Usuario in, String nome, String senha) {
        for (Usuario u : usuarios) {
            if ((Objects.equals(u.getNome(), nome) == true) && (Objects.equals(u.getSenha(), senha) == true)) {
                repasseDadosLogin(in, u);
                return true;
            }
        }
        return false;
    }
    public void repasseDadosLogin(Usuario in, Usuario u) {
        in.setNome(u.getNome());
        in.setEmail(u.getEmail());
        in.setSenha(u.getSenha());
        in.setAtivo(u.isAtivo());
        in.setPapel(u.getPapel());
    }

    public boolean trocarSenha(Usuario u, String senha) {
        if (Objects.equals(u.getSenha(), senha) == false) {
            u.setSenha(senha);
            return true;
        }
        return false;
    }
}
