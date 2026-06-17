package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DocenteData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.Docente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.StatusOportunidade;
import com.projetoextensao.lp2projetoextensaospring.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.DocenteRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;

import java.util.Objects;

@Service
public class DocenteService implements IntOportunidade {

    @Autowired
    private DocenteRepo docenteRepository;

    @Autowired
    private OportunidadeRepo oportunidadeRepository;

    @Autowired
    private OportunidadeService oportunidadeService;

    public Docente criarDocente(DocenteData data) {
        Docente novo = new Docente(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getSiape(),
                data.getDepartamento());
        return docenteRepository.save(novo);
    }

    @Override
    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        data.setAutor(d);
        data.setResponsavel(d);
        return oportunidadeService.criarOportunidade(u, d, data);
    }

    @Override
    public boolean publicar(Oportunidade op, Usuario u, StatusOportunidade status) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        if (op.getStatus() == StatusOportunidade.RASCUNHO || op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
            op.setStatus(status);
            oportunidadeRepository.save(op);
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
            oportunidadeRepository.save(op);
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
            oportunidadeRepository.save(op);
            return true;
        }
        return false;
    }

    @Override
    public boolean editarPlano(Oportunidade op, Usuario u, String novoPlano) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        op.setPlano(novoPlano);
        oportunidadeRepository.save(op);
        return true;
    }
}
