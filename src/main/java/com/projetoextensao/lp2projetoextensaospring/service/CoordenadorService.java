package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.CoordData;
import com.projetoextensao.lp2projetoextensaospring.dataTransfer.OportData;
import com.projetoextensao.lp2projetoextensaospring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.CoordenadorRepo;
import com.projetoextensao.lp2projetoextensaospring.repository.OportunidadeRepo;

@Service
public class CoordenadorService implements IntOportunidade {

    @Autowired
    private CoordenadorRepo coordenadorRepo;

    @Autowired
    private OportunidadeRepo oportunidadeRepo;

    @Autowired
    private OportunidadeService oportunidadeService;

    /**
     *
     * @param data recebe os dados para a atribuição de um novo coordenador
     * @return o novo coordenador salvo no banco de dados
     */

    public Coordenador criarCoordenador(CoordData data) {
        Coordenador novo = new Coordenador(data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getSiape(),
                data.getDepartamento());
        return coordenadorRepo.save(novo);
    }

    /**
     *
     * @param u recebe os dados do usuário que está criando a oportunidade
     * @param d recebe os dados do docente que será responsável pela oportunidade
     * @param data recebe os dados a serem inseridos na oportunidade em questão
     * @return
     */

    @Override
    public Oportunidade criarOportunidade(Usuario u, Docente d, OportData data) { //todo: revisar o parâmetro Usuario
        return oportunidadeService.criarOportunidade(u, d, data);
    }

    /**
     *
     * @param op recebe a oportunidade a ser publicada
     * @param u recebe o usuario que está publicando a oportunidade
     * @param status recebe o status da oportunidade em relação à sua publicação
     * @return true se a oportunidade puder ser publicada, se não, false
     */

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

    /**
     *
     * @param op recebe a oportunidade a ser fechada para inscrições e ter seus status mudado para Em Progresso
     * @param u recebe o usuário que está alterando o status da oportunidade
     * @return true se a oportunidade puder ser fechada, se não, false
     */

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

    /**
     *
     * @param op recebe a oportunidade que terá seu status alterado para Encerrada
     * @param u recebe o usuário que está encerrando a oportunidade
     * @return true se a oportunidade pôde ser fechada, se não, false
     */

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

    /**
     *
     * @param op recebe os dados da oportunidade a ter seu plano editado
     * @param u recebe o usuário que está realizando a modificação
     * @param novoPlano recebe uma string contendo o novo plano
     * @return true se a operação foi bem sucedida, se não, false
     */

    @Override
    public boolean editarPlano(Oportunidade op, Usuario u, String novoPlano) {
        if (op == null || u == null) return false;
        op.setPlano(novoPlano);
        oportunidadeRepo.save(op);
        return true;
    }
}
