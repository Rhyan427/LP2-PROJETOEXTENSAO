package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "discente_id")
public class Coordenador extends Docente {

    public Coordenador(String nome, String email, String senha, String siape, String departamento) {
        super(nome, email, senha, new Papel("Coordenador"), siape, departamento);
    }

    public Coordenador() {}
}

