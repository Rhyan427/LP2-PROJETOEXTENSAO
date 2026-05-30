package service;

import dataTransfer.DocenteData;
import dataTransfer.OportData;
import entity.Docente;
import entity.Oportunidade;
import entity.StatusOportunidade;
import entity.Usuario;

import java.util.Objects;

public class DocenteService implements IntOportunidade {
    OportunidadeService oportunidadeService = new OportunidadeService();

    public Docente criarDocente(DocenteData data) {
        Docente novo = new Docente(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getSiape(),
                data.getDepartamento());
        return novo;
    }

    @Override
    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        data.setAutor(d);
        data.setResponsavel(d);
        return oportunidadeService.criarOportunidade(data);
    }

    @Override
    public boolean publicar(Oportunidade op, Usuario u, StatusOportunidade status) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        if (op.getStatus() == StatusOportunidade.RASCUNHO || op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
            op.setStatus(status);
            return true;
        }
        return false;
    }

    @Override
    public boolean fecharInscricoes(Oportunidade op, Usuario u) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        if (op.getStatus() == StatusOportunidade.PUBLICADA) {
            op.setStatus(StatusOportunidade.EM_PROGRESSO);
            return true;
        }
        return false;
    }

    @Override
    public boolean encerrarOportunidade(Oportunidade op, Usuario u) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        if (op.getStatus() == StatusOportunidade.EM_PROGRESSO) {
            op.setStatus(StatusOportunidade.ENCERRADA);
            return true;
        }
        return false;
    }

    @Override
    public boolean editarPlano(Oportunidade op, Usuario u, String novoPlano) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        op.setPlano(novoPlano);
        return true;
    }
}
