package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.DocenteData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import com.projetoextensao.lp2projetoextensaospring.repository.CursoRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.PapelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.DocenteRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;

import java.util.Objects;
import java.util.Optional;

@Service
public class DocenteService implements IntOportunidade {

    @Autowired
    private DocenteRepo docenteRepository;

    @Autowired
    private OportunidadeRepo oportunidadeRepository;

    @Autowired
    private PapelRepo papelRepo;

    @Autowired
    private OportunidadeService oportunidadeService;

    /**
     *
     * @param data recebe os dados do docente para criar
     * @return salva os dados do docente criado
     */
    public Docente criarDocente(DocenteData data) {
        Docente novo = new Docente(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getSiape(),
                data.getDepartamento());
        Papel papel = papelRepo.findById(data.getPapel().getId())
                .orElseThrow(() -> new RuntimeException("Papel nao encontrado"));
        novo.setPapel(papel);
        novo.setAtivo(true);
        return docenteRepository.save(novo);
    }

    /**
     * @param u usuario que vai criar a oportunidade
     * @param d docente responsavel pela oportunidade
     * @param data dados da oportunidade a ser criada
     * @return salva a oportunidade no banco
     */
    @Override
    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) {
        data.setAutor(d);
        data.setResponsavel(d);
        return oportunidadeService.criarOportunidade(u, d, data);
    }

    /**
     *
     * @param op oportunidade a ser publicada
     * @param u usuario que publicara a oportunidade (o responsavel)
     * @param status status que a oportunidade assumira no processo
     * @return muda o status da oportunidade para o status informado e salva a oportunidade no banco
     */
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

    /**
     *
     * @param op oportunidade que tera as inscricoes fechadas
     * @param u usuario que fecha as inscricoes
     * @return muda o status da oportunidade e salva no banco
     */
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

    /**
     *
     * @param op oportunidade a ser encerrada
     * @param u usuario que encerra a oportunidade
     * @return muda o status da oportunidade para encerrada e salva no banco
     */
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

    /**
     *
     * @param op oportunidade que tera o plano alterado
     * @param u usuario que altera o plano
     * @param novoPlano plano novo que sera modificado naquela oportunidade
     * @return modifica o plano e salva no banco
     */
    @Override
    public boolean editarPlano(Oportunidade op, Usuario u, String novoPlano) {
        if (op == null || u == null) return false;
        if (!Objects.equals(u, op.getResponsavel())) return false;
        op.setPlano(novoPlano);
        oportunidadeRepository.save(op);
        return true;
    }

    /**
     *
     * @param id id do Docente que deseja encontrar
     * @return o Docente com o id informado
     */
    public Optional<Docente> buscarPorId(Integer id){
        return docenteRepository.findById(id);
    }
}
