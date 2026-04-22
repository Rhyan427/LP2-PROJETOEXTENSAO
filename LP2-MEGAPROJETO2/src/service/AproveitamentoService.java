package service;

import entity.Aproveitamento;
import entity.Certificado;
import entity.Discente;
import entity.StatusAproveitamento;

import java.util.ArrayList;
import java.util.Objects;

public class AproveitamentoService {
    ArrayList<Aproveitamento> lista = new ArrayList<>();

    public boolean uploadCertificado() {
        return false;
    } //TODO: fase 3

    public boolean criarAproveitamento(Discente di, Certificado cert) {
        if (di != null || cert != null) {
            Aproveitamento aproveitamento = new Aproveitamento(di, cert.getUuidHash(), cert.getOportunidade().getCargaHoraria(), StatusAproveitamento.PENDENTE);
            lista.add(aproveitamento);
            return true;
        }
        return false;
    }

    public void verAproveitamento() {
        for (Aproveitamento a : lista) {
            System.out.printf("- Aproveitamento %s, por %s. Status: %s\n", a.getDescricao(), a.getDiscente().getNome(), a.getStatus());
        }
    }

    public void verAproveitamentoPorDiscente(Discente di) {
        for (Aproveitamento a : lista) {
            if (Objects.equals(a.getDiscente(), di) == true) {
                System.out.printf("- Aproveitamento %s. Status: %s\n", a.getDescricao(), a.getStatus());
            }
        }
    }

    public boolean editarAproveitamento(String nome, String id, StatusAproveitamento status) {
        for (Aproveitamento a : lista) {
            if (Objects.equals(a.getDiscente().getNome(), nome) == true && Objects.equals(a.getDescricao(), id) == true) {
                a.setStatus(status);
                return true;
            }
        }
        return false;
    }
}
