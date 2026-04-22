package service;

import entity.Aviso;
import entity.DiscenteDiretor;
import entity.Docente;
import entity.Usuario;
import java.util.ArrayList;

public class AvisoService {
    private ArrayList<Aviso> avisos = new ArrayList<>();

    public ArrayList<Aviso> listarAvisos(){
        return avisos;
    }

    public boolean publicarAviso(Usuario autor, String titulo, String mensagem) {
        if (autor == null || titulo == null || mensagem == null) {
            System.out.println("Erro! Dados invalidos para publicar novo aviso"); //TODO: mover para Front
            return false;
        }

        if (autor instanceof Docente || autor instanceof DiscenteDiretor) {
            Aviso novo = new Aviso(titulo, mensagem, autor);
            avisos.add(novo);
            System.out.println("Aviso publicado por " + autor.getNome()); //TODO: mover para Front
            return true;
        } else {
            System.out.println("Acesso negado! Apenas docentes e discentes diretores podem publicar avisos."); //TODO: mover para Front
            return false;
        }
    }
}
