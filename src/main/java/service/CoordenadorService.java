package service;

import dataTransfer.CoordData;
import dataTransfer.OportData;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CoordenadorRepo;
import repository.OportunidadeRepo;

@Service
public class CoordenadorService implements IntOportunidade {

    @Autowired
    private CoordenadorRepo coordenadorRepo;

    @Autowired
    private OportunidadeRepo oportunidadeRepo;

    @Autowired
    private OportunidadeService oportunidadeService;

    public Coordenador criarCoordenador(CoordData data) {
        Coordenador novo = new Coordenador(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getSiape(),
                data.getDepartamento());
        return coordenadorRepo.save(novo);
    }

    @Override
    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        return oportunidadeService.criarOportunidade(u, d, data);
    }

    @Override
    public boolean publicar(Oportunidade op, Usuario u, StatusOportunidade status) {
        if (op == null || u == null) return false;

        if (op.getStatus() == StatusOportunidade.RASCUNHO || op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
            op.setStatus(status);
            oportunidadeRepo.save(op);
            return true;
        }
        return false;
    }

    @Override
    public boolean fecharInscricoes(Oportunidade op, Usuario u) {
        if (op == null || u == null) return false;
        if (op.getStatus() == StatusOportunidade.PUBLICADA) {
            op.setStatus(StatusOportunidade.EM_PROGRESSO);
            oportunidadeRepo.save(op);
            return true;
        }
        return false;
    }

    @Override
    public boolean encerrarOportunidade(Oportunidade op, Usuario u) {
        if (op == null || u == null) return false;
        if (op.getStatus() == StatusOportunidade.EM_PROGRESSO) {
            op.setStatus(StatusOportunidade.ENCERRADA);
            oportunidadeRepo.save(op);
            return true;
        }
        return false;
    }

    @Override
    public boolean editarPlano(Oportunidade op, Usuario u, String novoPlano) {
        if (op == null || u == null) return false;
        op.setPlano(novoPlano);
        oportunidadeRepo.save(op);
        return true;
    }
}
