package service;

import dataTransfer.CertData;
import entity.Certificado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CertificadoRepo;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepo repository;

    public Certificado criarCertificado(CertData data) {
        Certificado novo = new Certificado(data.getDiscente(),
                data.getOportunidade(),
                data.getOportunidade().getCargaHoraria(),
                "certificadoPath");
        return repository.save(novo);
    }

    public Certificado buscarPorUuid(String uuid){
        return repository.findById(uuid).orElse(null);
    }
}
