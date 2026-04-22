package service;

import entity.Coordenador;
import java.util.ArrayList;
import java.util.Objects;

public class CoordenadorService {
    ArrayList<Coordenador> coordenadores = new ArrayList<>();
    private CertificadoService certificadoService = new CertificadoService();

    public boolean criarCoordenador(String nome, String email, String senha, String siape, String departamento) {
        if (!coordenadorExiste(nome, email, siape)) {
            Coordenador coordenador = new Coordenador(nome, email, senha, siape, departamento);
            coordenadores.add(coordenador);
            return true;
        }
        return false;
    }
    public boolean coordenadorExiste(String nome, String email, String siape) {
        for (Coordenador coo : coordenadores) {
            if ((Objects.equals(coo.getNome(), nome) == true) || (Objects.equals(coo.getEmail(), email) == true) || (Objects.equals(coo.getSiape(), siape) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginCoordenador(Coordenador in, String nome, String senha) {
        for (Coordenador coo : coordenadores) {
            if ((Objects.equals(coo.getNome(), nome) == true) && (Objects.equals(coo.getSenha(), senha) == true)) {
                repasseDadosLogin(in, coo);
                return true;
            }
        }
        return false;
    }
    public void repasseDadosLogin(Coordenador in, Coordenador coo) {
        in.setNome(coo.getNome());
        in.setEmail(coo.getEmail());
        in.setSenha(coo.getSenha());
        in.setAtivo(coo.isAtivo());
        in.setPapel(coo.getPapel());
        in.setSiape(coo.getSiape());
        in.setDepartamento(coo.getDepartamento());
    }
}
