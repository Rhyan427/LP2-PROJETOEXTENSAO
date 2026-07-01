package com.projetoextensao.lp2projetoextensaospring.service;

import com.projetoextensao.lp2projetoextensaospring.dataTransfer.CertData;
import com.projetoextensao.lp2projetoextensaospring.entity.Certificado;
import com.projetoextensao.lp2projetoextensaospring.entity.Discente;
import com.projetoextensao.lp2projetoextensaospring.entity.Oportunidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoextensao.lp2projetoextensaospring.repository.CertificadoRepo;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepo repository;

    /**
     *
     * @param discente recebe o discente que irá receber o certificado
     * @param oportunidade recebe a oportunidade em questão
     * @return o certificado salvo no banco de dados
     */

    public Certificado criarCertificado(Discente discente, Oportunidade oportunidade) {
        Certificado novo = new Certificado(
                discente,
                oportunidade,
                oportunidade.getCargaHoraria(),
                "certificadoPath"
        );
        return repository.save(novo);
    }

    /**
     *
     * @param uuid recebe o identificador único do certificado
     * @return o certificado correspondente
     */

    public Certificado buscarPorUuid(String uuid){
        return repository.findById(uuid).orElse(null);
    }
}
