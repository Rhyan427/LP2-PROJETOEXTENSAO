package service;

import dataTransfer.CertData;
import entity.Certificado;

public class CertificadoService {
    public Certificado criarCertificado(CertData data) {
        Certificado novo = new Certificado(data.getDiscente(),
                data.getOportunidade(),
                data.getOportunidade().getCargaHoraria(),
                "certificadoPath");
        return novo;
    }
}
